package com.appstairs.multiplicationtable.Controller;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.appstairs.multiplicationtable.Base;
import com.appstairs.multiplicationtable.R;
import com.appstairs.multiplicationtable.Utils;

import java.util.Locale;

public class MultiplicationRecyclerController extends RecyclerView.Adapter<MultiplicationRecyclerController.ViewHolder>
        implements View.OnClickListener {

    private Context context;
    private int numOfCells;
    private @Base int matrixBase;

    public MultiplicationRecyclerController(Context context, int numOfCells, int base) {
        this.context = context;
        this.numOfCells = numOfCells;
        this.matrixBase = base;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView cellView;

        public ViewHolder(View v) {
            super(v);
            cellView = (TextView) v.findViewById(R.id.cell);
        }
    }

    @Override
    public MultiplicationRecyclerController.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell, null);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MultiplicationRecyclerController.ViewHolder holder, int cellIndex) {
        int colValue = cellIndex / numOfCells + 1;
        int rowValue = cellIndex % numOfCells + 1;

        //TODO if we want to use an object instead of views(according to OOP principles, we can use this:
        /*Cell cell = new Cell();
        cell.set(colValue, rowValue);*/
        //TODO Meantime, there is no need. Using Objects is overkill!

        //Here we initialize the cell content
        holder.cellView.setText(Utils.getInstance().convertToAnotherBase(matrixBase, colValue * rowValue));
        holder.cellView.setTag(colValue + ":" + rowValue);//We use tag to identify the cell location
        holder.cellView.setOnClickListener(this);
        if (colValue == rowValue)//bolds the prime numbers
            holder.cellView.setTextColor(Color.BLACK);
    }

    @Override
    public int getItemCount() {
        return numOfCells * numOfCells;
    }

    @Override
    public void onClick(View view) {
        String viewTag = view.getTag() + "";
        if (viewTag.contains(":")) {
            Toast.makeText(context, getNumericResult(viewTag), Toast.LENGTH_SHORT).show();
        }
    }

    private String getNumericResult(String viewTag) {
        Utils utils = Utils.getInstance();

        int columnInt = Integer.parseInt(viewTag.split(":")[0]);
        int rowInt = Integer.parseInt(viewTag.split(":")[1]);
        String column = utils.convertToAnotherBase(matrixBase, columnInt);
        String row = utils.convertToAnotherBase(matrixBase, rowInt);
        String result = utils.convertToAnotherBase(matrixBase, columnInt * rowInt);

        return String.format(Locale.getDefault(), "%s X %s = %s", column, row, result);
    }

}