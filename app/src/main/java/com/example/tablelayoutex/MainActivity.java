package com.example.tablelayoutex;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText edit1, edit2; //xml 에딧텍스트
    Button btnAdd, btnSub, btnMul, btnDiv; //xml 버튼 선언
    TextView textResult; //xml 텍스트뷰
    String num1, num2, result2; //String 문자형으로 result2는 String타입이기에 String타입으로 선언.
    Integer result; //Int의 wrapper class인 Integer
    //Wrapper class는 기본 자료타을 객체로 다루기 위해서 사용하는 클래스이다.
    Button[] numButtons = new Button[10];
    //Button[]은 배열이고, numButtons의 이름의 배열아래 10개의 버튼이 선언되어있다.
    Integer[] numBtnIds = {R.id.btn0,R.id.btn1,R.id.btn2,R.id.btn3,R.id.btn4,R.id.btn5,
            R.id.btn6,R.id.btn7,R.id.btn8,R.id.btn9};
            //Integer[]은 배열로 numBtnIds 이름의 배열아래 버튼 1~9 까지 들어있다.
    int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("테이블레이아웃 계산기");

        edit1 = (EditText) findViewById(R.id.edit1);
        edit2 = (EditText) findViewById(R.id.edit2);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnSub = (Button) findViewById(R.id.btnSub);
        btnMul = (Button) findViewById(R.id.btnMul);
        btnDiv = (Button) findViewById(R.id.btnDiv);
        textResult = (TextView) findViewById(R.id.textResult);

        btnAdd.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                num1 = edit1.getText().toString();
                num2 = edit2.getText().toString();
                result = Integer.parseInt(num1) + Integer.parseInt(num2);
                textResult.setText("계산 결과 : " + result.toString());
                return false;
            }
        });

        btnSub.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                num1 = edit1.getText().toString();
                num2 = edit2.getText().toString();
                result = Integer.parseInt(num1) - Integer.parseInt(num2);
                textResult.setText("계산 결과 : " + result.toString());
                return false;
            }
        });

        btnMul.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                num1 = edit1.getText().toString(); // edit1에 입력된 값을 문자열로 getText한다.
                num2 = edit2.getText().toString(); // edit2에 입력된 값을 문자열로 getText한다.
                result = Integer.parseInt(num1) * Integer.parseInt(num2);
                //Integer 정수형으로 num1 * num2를 계산하여 result에 저장한다.
                textResult.setText("계산 결과 : " + result.toString());
                return false;
            }
        });

        btnDiv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                num1 = edit1.getText().toString(); // edit1에 입력된 값을 문자열로 getText한다.
                num2 = edit2.getText().toString(); // edit2에 입력된 값을 문자열로 getText한다.
                Double result = Double.parseDouble(num1) / Double.parseDouble(num2);
                // 나눗셈으로 소수점 자리까지 출력을 위해 실수형 Double 사용 float보다 정밀도가 높다.
                result2 = String.format("%.1f", result); // 소수점 첫번째 자리까지 출력하는 메소드
                textResult.setText("계산 결과 : " + result2.toString()); // result2를 문자열로 가져와 setText한다.
                return false;
            }
        });

        for (i = 0; i < numBtnIds.length; i++) {
            numButtons[i] = (Button) findViewById(numBtnIds[i]); // 0~9까지의 버튼을 numBtnIds 지역변수로 선언했었다.
            //numButtons의 배열로 {0,1,2,3,4,5,6,7,8,9}로 10자리로 이루어져 있다.
            //이곳에서 버튼의 findViewById를 배열로 처리하였다.
        }

        for (i = 0; i < numBtnIds.length; i++) {
            final int index; // 익명의 클래스에 변수를 넣으려면 final이 반드시 필요하다.
            index = i; //index를 i로 바꿀 수 있다. this.i = i; 이런 식.

            numButtons[index].setOnClickListener(new View.OnClickListener() {
                @Override //클릭했을 때의 이벤트를 처리하는 익명 클래스
                public void onClick(View view) {
                    if (edit1.isFocused() == true) { // edit1을 클릭해서 집중시켰을 경우를 가르킨다.
                        num1 = edit1.getText().toString() + // String + String 은 문자열을 연결한다.
                                numButtons[index].getText().toString();
                        edit1.setText(num1); //edit1에 num1에 입력을 setText한다.
                    }
                    else if (edit2.isFocused() == true) { // edit2를 클릭해서 집중시켰을 경우를 가르킨다.
                        num2 = edit2.getText().toString() +
                                numButtons[index].getText().toString();
                        edit2.setText(num2); //edit2에 num2에 입력을 setText한다.
                    } else {
                        Toast.makeText(getApplicationContext(), "에디트텍스트를 선택해주세요",
                                Toast.LENGTH_SHORT).show(); // Focus되지 않았을경우 Toast메세지를 출력한다.
                    }
                }
            });
        }
    }
}