package com.hhd.java._Deme.Recycler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hhd.java.R;

import java.util.List;

/**
 * Created by HHD on 2016/10/20.
 * <p>
 * RecyclerView适配器（Adapter）
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> implements View.OnClickListener, View.OnLongClickListener {
    private Context context;
    private List<String> list;
    private OnChildClickListener listener;
    private OnChildLongClickListener longListener;
    private RecyclerView recyclerView;

    /**
     * 构造方法
     *
     * @param context 上下文
     * @param list    列表
     */
    public RecyclerAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    /**
     * 创建VH
     *
     * @param parent   父控件
     * @param viewType
     * @return
     */
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recycler_view, parent, false);
        view.setOnClickListener(this);
        view.setOnLongClickListener(this);
        return new MyViewHolder(view);
    }

    /**
     * 绑定（提供数据时调用）
     *
     * @param recyclerView 绑定对象
     */
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.recyclerView = recyclerView;
    }

    /**
     * 解绑
     *
     * @param recyclerView 解绑对象
     */
    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        this.recyclerView = null;
    }

    /**
     * VH绑定数据
     *
     * @param holder   要绑定的VH
     * @param position 数据在List的下标
     */
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.text.setText(list.get(position));
    }

    /**
     * Item的总数
     *
     * @return 列表的长度
     */
    @Override
    public int getItemCount() {//总数
        return list.size();
    }

    @Override
    public void onClick(View view) {//点击方法
        if (recyclerView != null && listener != null) {//后面加.if（加if和大括号）
            int position = recyclerView.getChildAdapterPosition(view);//.var（加int childAdapterPosition =）
            listener.onChildClick(recyclerView, view, position, list.get(position));
        }
    }

    /**
     * 多种布局重复利用
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    /**
     * 长按事件
     *
     * @param view 按到的view
     * @return
     */
    @Override
    public boolean onLongClick(View view) {
        if (recyclerView != null && longListener != null) {//后面加.if（加if和大括号）
            int position = recyclerView.getChildAdapterPosition(view);//.var（加int childAdapterPosition =）
            longListener.onChildLongClick(recyclerView, view, position, list.get(position));
        }
        return true;
    }

    /**
     * VH类主要是减少之后找组件
     */
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView text;

        public MyViewHolder(View itemView) {
            super(itemView);
            text = (TextView) itemView.findViewById(R.id.item_text);
        }
    }

    /**
     * 自定义点击接口
     */
    public interface OnChildClickListener {
        void onChildClick(RecyclerView parent, View view, int position, String data);
    }

    /**
     * 自定义长按接口
     */
    public interface OnChildLongClickListener {
        void onChildLongClick(RecyclerView parent, View view, int position, String data);
    }

    /**
     * 点击事件
     *
     * @param listener 事件监听
     */
    public void setOnChildClickListener(OnChildClickListener listener) {
        this.listener = listener;
    }

    /**
     * 长按事件
     *
     * @param longListener 事件监听
     */
    public void setOnChildLongClickListener(OnChildLongClickListener longListener) {
        this.longListener = longListener;
    }

    /**
     * 删除方法
     *
     * @param position 需要删除对应的下标
     */
    public void remove(int position) {//删除item
        list.remove(position);
//        notifyDataSetChanged();//不要使用改变数据（因为动画不会生效）
        notifyItemRemoved(position);//带动画
    }

    /**
     * 添加方法
     * @param position
     * @param data 新添加数据
     */
    public void add(int position,String data){
        list.add(position, data);
        notifyItemInserted(position);
    }

    /**
     * 改变方法
     * @param position
     * @param data
     */
    public void change(int position,String data){
        list.remove(position);//先删除之前数据
        list.add(position, data);//再添加改变的数据
        notifyItemChanged(position);
    }

}
