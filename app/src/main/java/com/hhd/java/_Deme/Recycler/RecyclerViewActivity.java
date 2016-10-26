package com.hhd.java._Deme.Recycler;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.hhd.java.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by HHD on 2016/10/20.
 *
 * RecyclerView测试界面
 */
public class RecyclerViewActivity extends Activity implements RecyclerAdapter.OnChildClickListener, RecyclerAdapter.OnChildLongClickListener {

    private RecyclerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        RecyclerView recycler= (RecyclerView) findViewById(R.id.recycler);
        List<String> list=new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(String.format(Locale.CHINA,"第%03d条数据",i+1));
        }
//        for (int i = 0; i < 100; i++) {
//            list.add(String.format(Locale.CHINA, "第%03d条数据%s", i + 1, i % 2 == 0 ? " 参差不齐 参差不齐 参差不齐" : ""));
//        }
        adapter=new RecyclerAdapter(this,list);

        /** this，显示方向，是否反转 */
        LinearLayoutManager lManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        /** this，列数，显示方向，是否反转 */
        GridLayoutManager gManaget=new GridLayoutManager(this,3,LinearLayoutManager.VERTICAL,false);
        /** 跨行或列 */
        gManaget.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (position == 0){
                    return 3;
                }
                return 1;
            }
        });
        /** 瀑布流：Staggered=参差不齐 */
        StaggeredGridLayoutManager sManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        /** 设置布局 */
        recycler.setLayoutManager(lManager);

        /** 自定义Recycler动画 */
        RecyclerItemAnimator dAnimator=new RecyclerItemAnimator();
        /** 删除动画 */
        dAnimator.setRemoveDuration(2000);//删除动画延迟2秒
        dAnimator.setMoveDuration(2000);//移动动画延迟2秒
        dAnimator.setAddDuration(2000);//添加动画延迟2秒

        dAnimator.setSupportsChangeAnimations(true);//把（默认不支持改变动画）设置为（支持改变动画）
        dAnimator.setChangeDuration(2000);//改变动画延迟2秒

        /** 标准的间隙方法 */
        recycler.addItemDecoration(new RecyclerView.ItemDecoration() {
            /** 绘制之前绘制方法 */
            @Override
            public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
                super.onDraw(c, parent, state);
                c.drawColor(Color.BLACK);//加黑色背景
            }
            /** 绘制前景（可以加动画、可以在Item上加内容...） */
            @Override
            public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
                super.onDrawOver(c, parent, state);
                /** 一般绘制Bitmap不写在onDrawOver方法中，因为这样会很耗费时间 */
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
                c.drawBitmap(bitmap,400,400,null);//资源，位置，位置，null
            }
            /** 绘制分割线宽高 */
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                int position = parent.getChildAdapterPosition(view);//为每个Item间隙不同提供控制变量
                outRect.set(2*position,2,2*position,0);//左、上、右、下边的间距
            }
        });

        /** 设置动画 */
        recycler.setItemAnimator(dAnimator);


        recycler.setAdapter(adapter);//判断数据是否为空
        adapter.setOnChildClickListener(this);//点击事件
        adapter.setOnChildLongClickListener(this);//长按事件
    }

    /**
     * 具体点击的业务逻辑
     *
     * @param parent 父组件
     * @param view 对应视图
     * @param position 对应视图下标
     * @param data 对应数据
     */
    @Override
    public void onChildClick(RecyclerView parent, View view, int position, String data) {
        Toast.makeText(this,data,Toast.LENGTH_SHORT).show();
    }

    /**
     * 具体长按的业务逻辑
     *
     * @param parent 父组件
     * @param view 对应视图
     * @param position 对应视图下标
     * @param data 对应数据
     */
    @Override
    public void onChildLongClick(RecyclerView parent, View view, int position, String data) {
//        adapter.remove(position);//删除item
//        adapter.add(position,"新增Item");//添加item
        adapter.change(position, "改变Item");//改变item
    }
}
