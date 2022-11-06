package com.phoenix.dikoin

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Observer
import com.phoenix.dikoin.ui.theme.DIKoinTheme
import com.phoenix.dikoin.ui.viewmodel.MainViewModel
import org.koin.android.ext.android.inject
import org.koin.android.scope.AndroidScopeComponent
import org.koin.androidx.scope.activityScope
import org.koin.androidx.compose.getViewModel
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope

class MainActivity: ComponentActivity(), AndroidScopeComponent {


    /**
     * use this in non composable project
     * */
//    private val  viewModel by viewModel<MainViewModel>()

    /**if and only if we want other object*/
//    private val api by inject<MyApi>()

    override val scope: Scope by activityScope()
//    override val scope: Scope by activityRetainedScope() //alive until the view model is alive doesn't work with compose
    private  val hello by inject<String>(named("hello"))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            println(hello)
            DIKoinTheme {
                //for composable project
                val viewModel = getViewModel<MainViewModel>()
                // A surface container using the 'background' color from the theme
                viewModel.response.observe(this, Observer {
                    Toast.makeText(this, it.status, Toast.LENGTH_SHORT).show()
                })

//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colors.background
//                ) {
//                    Greeting("Android")
//                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DIKoinTheme {
        Greeting("Android")
    }
}

/**
 *
 * */