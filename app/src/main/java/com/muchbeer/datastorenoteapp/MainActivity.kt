package com.muchbeer.datastorenoteapp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import com.muchbeer.datastorenoteapp.data.*
import com.muchbeer.datastorenoteapp.databinding.ActivityMainBinding
import com.muchbeer.datastorenoteapp.ui.TaskAdapter
import com.muchbeer.datastorenoteapp.ui.TasksViewModel
import com.muchbeer.datastorenoteapp.ui.TasksViewModelFactory

private const val USER_PREFERENCES_NAME = "user_preferences"

class MainActivity : AppCompatActivity() {

    private val Context.dataStore by preferencesDataStore(
        name = USER_PREFERENCES_NAME
    )

    private lateinit var viewModel: TasksViewModel

    private lateinit var binding : ActivityMainBinding
    private val adapter = TaskAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = TaskDatabase.getDatabase(this)

        viewModel = ViewModelProvider(
            this,
            TasksViewModelFactory(
                TaskRepository,
                UserPreferencesRepository(dataStore,db)
            )
        ).get(TasksViewModel::class.java)

        setupRecyclerView()

        setupOnCheckedChangeListeners()
        observePreferenceChanges()

 /*       viewModel.initialSetupEvent.observe(this) { initialSetupEvent ->
            updateTaskFilters(initialSetupEvent.sortOrder, initialSetupEvent.showCompleted)
            setupOnCheckedChangeListeners()
            observePreferenceChanges()
        }*/
    }

    private fun setupRecyclerView() {
// add dividers between RecyclerView's row items
        val decoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        binding.list.addItemDecoration(decoration)

        binding.list.adapter = adapter    }

    private fun observePreferenceChanges() {
        viewModel.tasksUiModel.observe(this) { tasksUiModel ->
            adapter.submitList(tasksUiModel.tasks)
            updateTaskFilters(tasksUiModel.sortOrder, tasksUiModel.showCompleted)
        }
    }

    private fun setupOnCheckedChangeListeners() {
        binding.sortDeadline.setOnCheckedChangeListener { _, checked ->
            viewModel.enableSortByDeadline(checked)
        }
        binding.sortPriority.setOnCheckedChangeListener { _, checked ->
            viewModel.enableSortByPriority(checked)
        }
        binding.showCompletedSwitch.setOnCheckedChangeListener { _, checked ->
            viewModel.showCompletedTasks(checked)
        }
    }

    private fun updateTaskFilters(sortOrder: SortOrder, showCompleted: Boolean) {
        with(binding) {
            showCompletedSwitch.isChecked = showCompleted
            sortDeadline.isChecked =
                sortOrder == SortOrder.BY_DEADLINE || sortOrder == SortOrder.BY_DEADLINE_AND_PRIORITY
            sortPriority.isChecked =
                sortOrder == SortOrder.BY_PRIORITY || sortOrder == SortOrder.BY_DEADLINE_AND_PRIORITY
        }
    }
}