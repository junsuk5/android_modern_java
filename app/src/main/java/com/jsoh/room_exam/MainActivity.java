package com.jsoh.room_exam;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

public class MainActivity extends AppCompatActivity {
    private EditText mTodoEditText;
    private TextView mResultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTodoEditText = findViewById(R.id.todo_edit);
        mResultTextView = findViewById(R.id.result_text);

        MainViewModel viewModel = ViewModelProviders.of(this)
                .get(MainViewModel.class);

        // UI 갱신
        viewModel.getAll().observe(this, todos -> {
            mResultTextView.setText(todos.toString());
        });

        // 버튼 클릭시 DB에 insert
        findViewById(R.id.add_button).setOnClickListener(view -> {
            viewModel.insert(new Todo(mTodoEditText.getText().toString()));
        });
    }

}
