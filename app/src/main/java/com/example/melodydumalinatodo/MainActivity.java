package com.example.melodydumalinatodo;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private String[] mTodos;
    private int mTodoIndex = 0;

    //variable used for the value of TODO_INDEX-------(this is for rotation fix)
    private static final String TODO_INDEX = "todoIndex";//-----

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // call the super class onCreate to complete the creation of activity like
        // the view hierarchy
        super.onCreate(savedInstanceState);

        // set the user interface layout for this Activity
        // the layout file is defined in the project res/layout/activity_todo.xml file
        setContentView(R.layout.activity_main);

        //---------------- test for this null state-----------------(this is for rotation fix)
        // check for saved state due to changes such as rotation or back button
        // and restore any saved state such as the todo index
        if ( savedInstanceState != null ) {

            //To the left of an equal sign, we have our mTodoIndex variable.
            // To the right, we're calling a method on Bundle that has the name savedInstanceState.
            // This method is getInt which is used to get an integer value from the Bundle.

            mTodoIndex = savedInstanceState.getInt( TODO_INDEX, 0 );

        }//--------------------------------------------------------------------------------

        // initialize member TextView so we can manipulate it later
        final TextView TodoTextView;
        TodoTextView = (TextView) findViewById(R.id.textViewTodo);

        // read the todo array from res/values/strings.xml
        Resources res = getResources();
        mTodos = res.getStringArray(R.array.todo);
        // display the first task from mTodo array in the TodoTextView
        TodoTextView.setText(mTodos[mTodoIndex]);

        Button buttonNext;
        Button buttonPrev;

        buttonNext = (Button) findViewById(R.id.buttonNext);
        buttonPrev = (Button) findViewById(R.id.buttonPrev);

        // OnClick listener for the  Next button
        buttonNext.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                mTodoIndex = (mTodoIndex + 1) % mTodos.length;
                TodoTextView.setText(mTodos[mTodoIndex]);

            }
        });

        buttonPrev.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                mTodoIndex = (mTodoIndex - 1) % mTodos.length;

                if(mTodoIndex == -1){
                    mTodoIndex = mTodos.length - 1 ;
                    TodoTextView.setText(mTodos[mTodoIndex]);
                }
                else {
                    TodoTextView.setText(mTodos[mTodoIndex]);
                }
            }
        });
    }

    //-----------Before our Activity is destroyed when the screen is rotated,------(this is for rotation fix)
    // Android allows you to override a method called onSaveInstanceState
    @Override
    public void onSaveInstanceState(Bundle mySavedInstanceState ) {

        super.onSaveInstanceState(mySavedInstanceState);

        mySavedInstanceState.putInt( TODO_INDEX, mTodoIndex );

    }///------------------------------------------------------------------
}
