package com.example.costmanager.Expense;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.example.costmanager.Expense.Database.Expense;
import com.example.costmanager.Income.Database.Income;
import com.example.costmanager.Income.IncomeListAdapter;
import com.example.costmanager.Income.IncomeViewModel;

import java.util.ArrayList;
import java.util.List;

public class ExpenseListAdapter extends RecyclerView.Adapter<ExpenseListAdapter.ViewHolder> {
    private List<Expense> expenseList = new ArrayList<>();
    private ExpenseViewModel expenseVm;
    Context context;
    private ExpenseAdapterCallback expenseAdapterCallback;

    interface ExpenseAdapterCallback{
        void update(Expense expense);
    }

    public ExpenseListAdapter(Context applicationContext) {
        this.context = applicationContext;
        this.expenseAdapterCallback = (ExpenseActivity)applicationContext;
        expenseVm = ViewModelProviders.of((ExpenseActivity)context).get(ExpenseViewModel.class);
    }

    public void setIncomeList(List<Expense> expenseList) {
        this.expenseList = expenseList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
