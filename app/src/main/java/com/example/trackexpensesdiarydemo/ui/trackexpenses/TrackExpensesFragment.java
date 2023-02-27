package com.example.trackexpensesdiarydemo.ui.trackexpenses;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.trackexpensesdiarydemo.databinding.FragmentTrackexpensesBinding;
import com.example.trackexpensesdiarydemo.databinding.FragmentHomeBinding;
import com.example.trackexpensesdiarydemo.databinding.FragmentTrackexpensesBinding;

import java.util.ArrayList;
import java.util.Calendar;

public class TrackExpensesFragment extends Fragment {
    FragmentTrackexpensesBinding binding;
    private static StringBuilder show_equation;//顯示運算式
    private static ArrayList calculate_equation;//計算式
    /*public SQLdata DH=null;*/

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = FragmentTrackexpensesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        binding.CountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(getActivity()).setTitle("回報計算結果").setMessage("本次記帳結果為:" + binding.historyNumberView.getText() + "+" + binding.incomeNumberVIew.getText() + "-" + binding.costNumberView.getText() + "=" + (binding.historyNumberView.getText() + "+" + binding.incomeNumberVIew.getText() + "-" + binding.costNumberView.getText())).setPositiveButton("儲存此結果", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                                                
                    }
                }).setNegativeButton("返回", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();
            }
        });

        binding.calendarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int y = calendar.get(calendar.YEAR);
                int m = calendar.get(calendar.MONTH);
                int d = calendar.get(calendar.DATE);
                DatePickerDialog dialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        binding.calendarBtn.setText(year + "/" + (month + 1) + "/" + dayOfMonth);
                    }
                }, y, m, d);
                dialog.show();
            }
        });
        //初始化
        show_equation = new StringBuilder();
        calculate_equation = new ArrayList<>();
        binding.zeroBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(show_equation.equals("0"))) {
                    show_equation.append("0");
                    binding.calculatorNuberView.setText(show_equation);
                }
            }
        });
        binding.oneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show_equation.append("1");
                binding.calculatorNuberView.setText(show_equation);
            }
        });
        binding.twoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show_equation.append("2");
                binding.calculatorNuberView.setText(show_equation);
            }
        });
        binding.threeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show_equation.append("3");
                binding.calculatorNuberView.setText(show_equation);
            }
        });
        binding.fourBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show_equation.append("4");
                binding.calculatorNuberView.setText(show_equation);
            }
        });
        binding.fiveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show_equation.append("5");
                binding.calculatorNuberView.setText(show_equation);
            }
        });
        binding.sixBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show_equation.append("6");
                binding.calculatorNuberView.setText(show_equation);
            }
        });
        binding.sevenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show_equation.append("7");
                binding.calculatorNuberView.setText(show_equation);
            }
        });
        binding.eightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show_equation.append("8");
                binding.calculatorNuberView.setText(show_equation);
            }
        });
        binding.nineBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show_equation.append("9");
                binding.calculatorNuberView.setText(show_equation);
            }
        });
        binding.CBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show_equation.delete(0, show_equation.length());
                calculate_equation.clear();
                binding.calculatorNuberView.setText("");
            }
        });
        binding.backSpaceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(show_equation.equals(""))) {
                    show_equation.deleteCharAt(show_equation.length() - 1);
                    binding.calculatorNuberView.setText(show_equation);
                }
            }
        });
        binding.equalsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (show_equation.charAt(0) == '-')
                    show_equation.insert(0, "0");
                StringBuilder temp1 = new StringBuilder();
                for (int i = 0; i < show_equation.length(); i++) {
                    if (show_equation.charAt(i) >= '0' && show_equation.charAt(i) <= '9' || show_equation.charAt(i) == '.') {
                        temp1.append(String.valueOf(show_equation.charAt(i)));
                    } else {
                        calculate_equation.add(temp1.toString());
                        temp1.delete(0, temp1.length());
                        calculate_equation.add(String.valueOf(show_equation.charAt(i)));
                    }
                }
                calculate_equation.add(temp1.toString());
                calculate_equation.add("#");
                String temp8 = calculate(calculate_equation);
                binding.calculatorNuberView.setText(temp8);
            }
        });
        binding.addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show_equation.append("+");
                binding.calculatorNuberView.setText(show_equation);
            }
        });
        binding.minusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show_equation.append("-");
                binding.calculatorNuberView.setText(show_equation);
            }
        });
        binding.multiplyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show_equation.append("*");
                binding.calculatorNuberView.setText(show_equation);
            }
        });
        return root;
    }

    protected boolean operatorPriorityCompare(char operator1, char operator2) {
        int o1 = 0;
        int o2 = 0;
        switch (operator1) {
            case '+': {
                o1 = 0;
                break;
            }
            case '-': {
                o1 = 0;
                break;
            }
            case '*': {
                o1 = 1;
                break;
            }
        }
        switch (operator2) {
            case '+': {
                o2 = 0;
                break;
            }
            case '-': {
                o2 = 0;
                break;
            }
            case '*': {
                o2 = 1;
                break;
            }
        }
        if (o1 <= o2) {
            return false;
        } else
            return true;
    }

    protected String calculate(ArrayList equation) {
        Double temp2;
        Double temp3;
        Double result;
        ArrayList operator = new ArrayList();
        ArrayList operand = new ArrayList();
        for (int i = 0; i < equation.size(); i++) {
            String temp4 = (String) equation.get(i);
            if (temp4.equals("+") || temp4.equals("-") || temp4.equals("*") || temp4.equals("/")) {
                if (operator.size() > 0) {
                    String temp5 = operator.get(operator.size() - 1).toString();
                    while (!(operatorPriorityCompare(temp4.charAt(0), temp5.charAt(0))) && operator.size() > 0) {
                        operator.remove(operator.size() - 1);
                        temp3 = (Double.parseDouble(operand.get(operand.size() - 1).toString()));
                        operand.remove(operand.size() - 1);
                        temp2 = (Double.parseDouble(operand.get(operand.size() - 1).toString()));
                        operand.remove(operand.size() - 1);
                        switch (temp5.charAt(0)) {
                            case '+': {
                                result = temp2 + temp3;
                                operand.add(String.valueOf(result));
                                break;
                            }
                            case '-': {
                                result = temp2 - temp3;
                                operand.add(String.valueOf(result));
                                break;
                            }
                            case '*': {
                                result = temp2 * temp3;
                                operand.add(String.valueOf(result));
                                break;
                            }
                        }
                        if (operator.size() > 0) {
                            temp5 = operator.get(operator.size() - 1).toString();
                        } else
                            break;
                    }
                    operator.add(temp4);
                } else
                    operator.add(temp4);
            } else if (temp4.equals("#")) {
                while (operator.size() > 0) {
                    String temp6 = (String) operator.get(operator.size() - 1);
                    operator.remove(operator.size() - 1);
                    temp3 = (Double.parseDouble(operand.get(operand.size() - 1).toString()));
                    operand.remove(operand.size() - 1);
                    temp2 = (Double.parseDouble(operand.get(operand.size() - 1).toString()));
                    operand.remove(operand.size() - 1);
                    switch (temp6.charAt(0)) {
                        case '+': {
                            result = temp2 + temp3;
                            operand.add(String.valueOf(result));
                            break;
                        }
                        case '-': {
                            result = temp2 - temp3;
                            operand.add(String.valueOf(result));
                            break;
                        }
                        case '*': {
                            result = temp2 * temp3;
                            operand.add(String.valueOf(result));
                            break;
                        }
                    }
                }
            } else {
                operand.add(temp4);
            }
        }
        return operand.get(0).toString();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}