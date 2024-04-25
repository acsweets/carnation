package com.example.lily02
import androidx.lifecycle.viewmodel.compose.viewModel
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.magnifier
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lily02.data.Task
import com.example.lily02.model.TodoListViewModel
import com.example.lily02.ui.theme.CarnationTheme
import androidx.compose.runtime.livedata.observeAsState

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CarnationTheme {
                // A surface container using the 'background' color from the theme
//                TodoApp()
                TodoAppViewModel()
            }
        }
    }
}

val dummyTasks = mutableListOf(
    Task(id = 1, title = "买菜"),
    Task(id = 2, title = "做饭"),
    Task(id = 3, title = "洗衣服")
)

@Composable
fun TodoListScreen(
    tasks: List<Task>,
    onTaskCheckedChange: (Int, Boolean) -> Unit,
    delete: (Int) -> Unit
) {
    LazyColumn {
        items(tasks) { task ->
            TaskItem(task = task, onTaskCheckedChange = onTaskCheckedChange, delete)
        }

    }
}

@Composable
fun TaskItem(task: Task, onTaskCheckedChange: (Int, Boolean) -> Unit, delete: (Int) -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(16.dp)
    ) {
        Checkbox(
            checked = task.completed,
            onCheckedChange = { isChecked ->
                onTaskCheckedChange(task.id, isChecked)
            }
        )
        Text(
            text = task.title,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(start = 8.dp)
        )

        Button(onClick = {
            delete(task.id)
        }, modifier = Modifier.absolutePadding(left = 50.dp)) {
            Text(text = "删除")
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "MutableCollectionMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoApp() {
    // remember 只会记住重组后的 但不会帮助您在配置更改后保持状态
    //rememberSaveable 置更改后保持状态（横竖屏的切换）

//mutableStateListOf不能被委托的原因委托属性需要实现 getValue() 和 setValue() 方法。
// 而 mutableStateListOf<Task>() 本身并没有实现这两个方法,因此无法作为委托属性使用。

//   val todosList = rememberSaveable { mutableStateListOf<Task>() }

    var tasks by remember { mutableStateOf(dummyTasks) }
    var newTodo by remember { mutableStateOf("") }

    Scaffold(
        content = {
            Column {
                CenterAlignedTopAppBar(title = { Text(text = "TodoList") })
                Spacer(modifier = Modifier.height(10.dp))
                TodoListScreen(tasks = tasks, { taskId, isChecked ->
                    tasks = tasks.map { task ->
                        if (task.id == taskId) {
                            task.copy(completed = isChecked)
                        } else {
                            task
                        }
                    }.toMutableList()
                },  { taskId ->
                    tasks = tasks.filter { it.id != taskId }.toMutableList()
                })
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    TextField(
                        value = newTodo,
                        onValueChange = { newTodo = it },
                        modifier = Modifier.weight(1f)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(
                        onClick = {
                            tasks += Task(id = tasks.size + 1, title = newTodo)

                        }
                    ) {
                        Text(text = "添加")
                    }
                }
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "MutableCollectionMutableState")
@Composable
fun TodoListWithAddDelete() {
    var todos by remember { mutableStateOf(mutableListOf("Todo 1", "Todo 2")) }
    var newTodo by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        // 添加新的待办事项
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            TextField(
                value = newTodo,
                onValueChange = { newTodo = it },
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Button(
                onClick = {
                    if (newTodo.isNotBlank()) {
                        todos.add(newTodo)
                        newTodo = ""
                    }
                }
            ) {
                Text(text = "添加")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 显示当前的待办事项列表
        Column {
            todos.forEach { todo ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = todo, modifier = Modifier.weight(1f))
                    Button(onClick = { todos.remove(todo) }) {
                        Text(text = "删除")
                    }
                }
            }
        }
    }
}



@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoAppViewModel(viewModel: TodoListViewModel = TodoListViewModel()) {
    val tasks by viewModel.tasks.observeAsState(viewModel.dummyTaskModels)
    var newTodo by remember { mutableStateOf("") }
    Scaffold(
        content = {
            Column {
                CenterAlignedTopAppBar(title = { Text(text = "TodoList") })
                Spacer(modifier = Modifier.height(10.dp))
                TodoListScreen(
                    tasks = tasks,
                    onTaskCheckedChange = { taskId, isChecked ->
                        viewModel.updateTask(taskId, isChecked)
                    },
                    delete = { taskId ->
                        viewModel.deleteTask(taskId)
                    }
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    TextField(
                        value = newTodo,
                        onValueChange = { newTodo = it },
                        modifier = Modifier.weight(1f)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(
                        onClick = {
                            viewModel.addTask(newTodo)
                            newTodo = ""
                        }
                    ) {
                        Text(text = "添加")
                    }
                }
            }
        }
    )
}