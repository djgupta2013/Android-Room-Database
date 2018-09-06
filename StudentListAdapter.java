package com.wildnet.mvvmroomexample;

import android.app.AlertDialog;
import android.app.Application;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wildnet.mvvmroomexample.model.Student;
import com.wildnet.mvvmroomexample.repository.StudentRepository;
import com.wildnet.mvvmroomexample.viewModel.StudentViewModel;

import java.util.List;

import static android.content.ContentValues.TAG;

public class StudentListAdapter extends RecyclerView.Adapter<StudentListAdapter.StudentViewHolder>
        implements View.OnTouchListener{

    private Context context;
    private Application application;
    private StudentViewModel mStudentViewModel;

    class StudentViewHolder extends RecyclerView.ViewHolder{

        private final TextView email,name,password;
        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            email=itemView.findViewById(R.id.tv_email);
            name=itemView.findViewById(R.id.tv_name);
            password=itemView.findViewById(R.id.tv_password);
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    final int position=getAdapterPosition();
                    Log.e("position", "position"+position);
                    String email=mStudents.get(position).getStudentEmail();
                    Log.e("position", "position "+position+" "+email);
                    final AlertDialog.Builder dialog=new AlertDialog.Builder(context);
                    dialog.setTitle("Confirm Delete...");
                    dialog.setCancelable(false);
                    dialog.setMessage("Are you sure you want delete this?").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                        Student student=mStudents.get(position);
                        mStudentViewModel=ViewModelProviders.of((FragmentActivity) context).get(StudentViewModel.class);
                        mStudentViewModel.deleteUser(student);

                            //mStudentViewModel = ViewModelProviders.of(this).get(StudentViewModel.class);


                       //new StudentRepository((Application) context).deleteUser(student);

                            /*databaseHelper.deleteData(email);
                            list.remove(position);*/
                        }
                    }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    }).show();
                    Log.e(TAG, "onLongClick: "+position );
                    return true;
                }
            });
        }
    }

    private final LayoutInflater mInflater;
    private List<Student> mStudents; // Cached copy of words

    StudentListAdapter(Context context){
        this.context=context;
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, viewGroup, false);
        return new StudentViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        if (mStudents != null) {
            Student current = mStudents.get(position);
            holder.email.setText(current.getStudentEmail());
            holder.name.setText(current.getStudentName());
            holder.password.setText(current.getStudentPassword());
        } else {
            // Covers the case of data not being ready yet.
            holder.email.setText("No Word");
        }
    }

    void setmStudents(List<Student> students){
        mStudents = students;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mStudents != null)
            return mStudents.size();
        else return 0;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }
}
