package com.example.costmanager.Income;

import android.content.Context;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Update;

import com.example.costmanager.Income.Database.Income;
import com.example.costmanager.R;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class IncomeListAdapter extends RecyclerView.Adapter<IncomeListAdapter.ViewHolder> {
    private List<Income> incomeList = new ArrayList<>();
    private IncomeViewModel incomeVm;
    Context context;
    private InocomeAdapterCallback inocomeAdapterCallback;

    interface InocomeAdapterCallback{
        void update(Income income);
    }

    public IncomeListAdapter(Context applicationContext){//, InocomeAdapterCallback inocomeAdapterCallback) {
        this.context = applicationContext;
        this.inocomeAdapterCallback = (IncomeActivity)applicationContext;
        incomeVm = ViewModelProviders.of((IncomeActivity)context).get(IncomeViewModel.class);
    }

    public void setIncomeList(List<Income> incomeList) {
        this.incomeList = incomeList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.each_income, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Income income = incomeList.get(position);
        holder.incomeVal.setText(String.valueOf(income.getAmount()));
        holder.type.setText(income.getType());
        holder.name.setText(income.getName());
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Income income1 = new Income();
                income1.setId(income.getId());
                income1.setType(income.getType());
                income1.setAmount(income.getAmount());
                income1.setName(income.getName());
                income1.setUid(income.getUid());
                updateItem(income1);
            }

            private void updateItem(Income income) {
                AddIncomeDialog dialog = new AddIncomeDialog();
                inocomeAdapterCallback.update(income);
//                dialog.show(, null);
            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteItem(income.getId());
            }

            private void deleteItem(long id) {
                new DeleteVM((IncomeActivity)context).execute(id);
            }
        });
    }

    @Override
    public int getItemCount() {
        return incomeList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView incomeVal, type, name;
        private Button edit, delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            incomeVal = itemView.findViewById(R.id.income_val);
            type = itemView.findViewById(R.id.type);
            name = itemView.findViewById(R.id.name);
            edit = itemView.findViewById(R.id.edit);
            delete = itemView.findViewById(R.id.delete);
        }
    }

    class DeleteVM extends AsyncTask<Long, Void, Boolean>
    {
        private WeakReference<IncomeActivity> activityReference;

        public DeleteVM(IncomeActivity context){
            activityReference = new WeakReference<>(context);
        }
        public void deleteVal(Long myModel)
        {
            incomeVm.deleteData(myModel);
        }

        @Override
        protected Boolean doInBackground(Long... myModels) {
            deleteVal(myModels[0]);
            return true;
        }
    }

    class UpdateVM extends AsyncTask<Income, Void, Boolean>
    {
        private WeakReference<IncomeActivity> activityReference;

        public UpdateVM(IncomeActivity context){
            activityReference = new WeakReference<>(context);
        }
        public void deleteVal(Income myModel)
        {
            incomeVm.updateData(myModel);
        }

        @Override
        protected Boolean doInBackground(Income... myModels) {
            deleteVal(myModels[0]);
            return true;
        }
    }
}
