package com.example.costmanager.Expense;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.example.costmanager.Expense.Database.Expense;
import com.example.costmanager.R;

public class AddExpenseDialog extends DialogFragment {
    AddExpenseDialog.ExpenseDialogListner listner;
    Expense expense = new Expense();

    public interface ExpenseDialogListner {
        void selected(String name, String type, int amount, Long id);
    }

    public AddExpenseDialog() {
    }

    public AddExpenseDialog(Expense expense) {
        this.expense = expense;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        listner = (AddExpenseDialog.ExpenseDialogListner)getContext();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = getViewForExpenseDialog(inflater);
        builder.setView(view)
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getEnteredValues(dialog);
                    }

                    private void getEnteredValues(DialogInterface dialog) {
                        EditText nameView = ((AlertDialog) dialog).findViewById(R.id.enter_expense_name);
                        RadioGroup typeGroup = ((AlertDialog) dialog).findViewById(R.id.expense_type_group);
                        RadioButton typeButton = ((AlertDialog) dialog).findViewById(typeGroup.getCheckedRadioButtonId());
                        EditText amountView = ((AlertDialog)dialog).findViewById(R.id.enter_expense_price);

                        if(nameView.getText().toString().isEmpty()) {
                            Toast.makeText(getContext(),"Please Enter Expense Title", Toast.LENGTH_SHORT).show();
                        } else if(amountView.getText().toString().isEmpty()) {
                            Toast.makeText(getContext(), "Please Enter Expense Price", Toast.LENGTH_SHORT).show();
                        } else {
                            String val = typeButton.getText().toString();
                            String name = nameView.getText().toString();
                            int amount = Integer.parseInt(amountView.getText().toString());
                            listner.selected(name, val, amount, expense.getId());
                            Toast.makeText(getContext(),"Expense Save Successfully.", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .setCancelable(true);
        return builder.create();
    }

    private View getViewForExpenseDialog(LayoutInflater inflater) {
        View view = inflater.inflate(R.layout.layout_expense,null);
        final LinearLayout layout_month = view.findViewById(R.id.month_option_type);
        final LinearLayout layout_part = view.findViewById(R.id.part_option_type);
        RadioGroup typeGroup = view.findViewById(R.id.expense_type_group);

        RadioButton typePart = view.findViewById(R.id.type_part);
        typePart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout_month.setVisibility(LinearLayout.GONE);
                layout_part.setVisibility(LinearLayout.VISIBLE);
            }
        });
        RadioButton typeMonthly = view.findViewById(R.id.type_monthly);
        typeMonthly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout_part.setVisibility(LinearLayout.GONE);
                layout_month.setVisibility(LinearLayout.VISIBLE);
            }
        });

//        typeGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                switch (checkedId) {
//                    case R.id.type_monthly :
//                        Log.d("clicked on mmonthly - ",Integer.toString(R.id.type_monthly));
//                        layout_month.setVisibility(LinearLayout.VISIBLE);
//                        layout_part.setVisibility(LinearLayout.GONE);
//                    case R.id.type_part :
//                        layout_part.setVisibility(LinearLayout.VISIBLE);
//                        layout_month.setVisibility(LinearLayout.GONE);
//                }
//                Log.d("changed","value changed is - "+Integer.toString(checkedId));
//            }
//        });
        if (expense.getName() != null){
            EditText entername = view.findViewById(R.id.enter_expense_name);
            EditText enterprice = view.findViewById(R.id.enter_expense_price);
//            LinearLayout layout_month = view.findViewById(R.id.month_option);
//            LinearLayout layout_part = view.findViewById(R.id.part_option);
            EditText monthDate = view.findViewById(R.id.month_date);
            EditText parts = view.findViewById(R.id.part_numbers);

            switch (expense.getType()){
                case "Monthly" :
//                    RadioButton typeMonthly = view.findViewById(R.id.type_monthly);
                    typeMonthly.setChecked(true);
                    monthDate.setText(Integer.toString(expense.getDate()));
                    layout_part.setVisibility(LinearLayout.GONE);
                    layout_month.setVisibility(LinearLayout.VISIBLE);
                case "Parts" :
//                    RadioButton typePart = view.findViewById(R.id.type_part);
                    typePart.setChecked(true);
                    parts.setText(Integer.toString(expense.getParts()));
                    layout_month.setVisibility(LinearLayout.GONE);
                    layout_part.setVisibility(LinearLayout.VISIBLE);
            }
            entername.setText(expense.getName());
            enterprice.setText(Integer.toString(expense.getPrice()));
        }
        return view;
    }
}
