package com.example.costmanager.Income;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.example.costmanager.Income.Database.Income;
import com.example.costmanager.R;

public class AddIncomeDialog extends DialogFragment {

    IncomeDialogListner listner;
    Income income = new Income();

    public interface IncomeDialogListner {
        public void selected(String name, String type, int amount, Long id);
    }

    public AddIncomeDialog() {
    }

    public AddIncomeDialog(Income income) {
        this.income = income;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        listner = (IncomeDialogListner)getContext();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = getViewForDialog(inflater);
        builder.setView(view)
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getEnteredValues(dialog);
                    }

                    private void getEnteredValues(DialogInterface dialog) {
                        EditText nameView = ((AlertDialog) dialog).findViewById(R.id.enter_name);
                        RadioGroup typeGroup = ((AlertDialog) dialog).findViewById(R.id.income_type_group);
                        RadioButton typeButton = ((AlertDialog) dialog).findViewById(typeGroup.getCheckedRadioButtonId());
                        String val = typeButton.getText().toString();
                        EditText amountView = ((AlertDialog)dialog).findViewById(R.id.enter_amount);

                        if(nameView.getText().toString().isEmpty()) {
                            Toast.makeText(getContext(), "Please Enter title of Income", Toast.LENGTH_SHORT).show();
                        } else if(amountView.getText().toString().isEmpty()) {
                            Toast.makeText(getContext(), "Please Enter Valid Amount.", Toast.LENGTH_SHORT).show();
                        } else {
                            String name = nameView.getText().toString();
                            int amount = Integer.parseInt(amountView.getText().toString());
                            listner.selected(name, val, amount, income.getId());
                            Toast.makeText(getContext(), "Income Added Successfully.", Toast.LENGTH_SHORT).show();
                        }

                    }
                })
                .setCancelable(true);
        return builder.create();
    }

    private View getViewForDialog(LayoutInflater inflater) {
        View view = inflater.inflate(R.layout.layout_income,null);
        if (income.getName() != null){
            EditText entername = view.findViewById(R.id.enter_name);
            EditText enteramount = view.findViewById(R.id.enter_amount);
            switch (income.getType()){
                case "Monthly" :
                    RadioButton typeMonthly = view.findViewById(R.id.type_monthly);
                    typeMonthly.setChecked(true);
                case "Weekly" :
                    RadioButton typeWeekly = view.findViewById(R.id.type_weekly);
                    typeWeekly.setChecked(true);
            }
            entername.setText(income.getName());
            enteramount.setText(Integer.toString(income.getAmount()));
        }
        return view;
    }
}
