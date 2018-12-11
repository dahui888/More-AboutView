package comt.leo.picker.moreaboutview.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import java.util.ArrayList;

import comt.leo.picker.moreaboutview.R;
import comt.leo.picker.moreaboutview.activity.BezierAbout.BezierActivity;
import comt.leo.picker.moreaboutview.adapter.RecycleAdapter;
import comt.leo.picker.moreaboutview.bean.MessageBean;
import comt.leo.picker.moreaboutview.recyevent.OnRecyclerItemClickListener;
import comt.leo.picker.moreaboutview.recyevent.RecyItemTouchHelperCallback;
import comt.leo.picker.moreaboutview.utils.AnimationItem;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private AnimationItem animationItem;//recycleView动画类

    private RecyclerView recycleView;
    private RecycleAdapter adapter;
    private ArrayList<MessageBean> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        animationItem = new AnimationItem("Scale random", R.anim.layout_animation_from_bottom);
        recycleView = findViewById(R.id.recycleView);


        arrayList.add(new MessageBean(1, "自定义控件--基础篇(简单绘制矩形)"));
        arrayList.add(new MessageBean(2, "自定义控件--基础篇(自定义view及动画)"));
        arrayList.add(new MessageBean(3, "自定义控件--基础篇(简单的自定义属性)"));
        arrayList.add(new MessageBean(4, "自定义控件--基础篇(绘制文字)"));
        arrayList.add(new MessageBean(5, "自定义控件--基础篇(环形进度条)"));
        arrayList.add(new MessageBean(6, "自定义控件--基础篇(拓展篇-镂空布局)"));
        arrayList.add(new MessageBean(7, "自定义控件--基础篇(拓展篇-3D球状 TagCloudView)"));
        arrayList.add(new MessageBean(8, "自定义控件--基础篇(色彩矩阵篇)"));
        arrayList.add(new MessageBean(9, "自定义控件--基础篇(PorterDuffXfermode学习)"));
        arrayList.add(new MessageBean(10, "自定义控件--贝塞尔曲线学习"));
        arrayList.add(new MessageBean(11, "自定义控件--休闲篇(2年前改的github上的项目,从中收益匪浅)"));
        adapter = new RecycleAdapter(this, this);
        adapter.setData(arrayList);

        GridLayoutManager layoutManager11 = new GridLayoutManager(this, 3);
        recycleView.setLayoutManager(layoutManager11);
        recycleView.setHasFixedSize(true);

        /*
         * 最后一个参数是 第一个item能否被拖拽
         * */
        RecyItemTouchHelperCallback itemTouchHelperCallback = new RecyItemTouchHelperCallback(adapter, false, false);
        final ItemTouchHelper itemTouchHelper = new ItemTouchHelper(itemTouchHelperCallback);
        itemTouchHelper.attachToRecyclerView(recycleView);

        recycleView.addOnItemTouchListener(new OnRecyclerItemClickListener(recycleView) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder viewHolder) {
            }

            @Override
            public void onLongClick(RecyclerView.ViewHolder viewHolder) {
                if (viewHolder.getLayoutPosition() != 0) {
                    itemTouchHelper.startDrag(viewHolder);
                }
            }
        });


        recycleView.setAdapter(adapter);
        runLayoutAnimation(recycleView, animationItem);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.relativeBg:
                MessageBean messageBean = (MessageBean) view.getTag();
                switch (messageBean.getPosition()) {
                    case 1:
                        startActivity(new Intent(MainActivity.this, MyViewandOnewActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(MainActivity.this, MyViewandTwoActivity.class));
                        break;
                    case 3:
                        startActivity(new Intent(MainActivity.this, MyViewandThreeActivity.class));
                        break;
                    case 4:
                        startActivity(new Intent(MainActivity.this, MyViewandFourActivity.class));
                        break;
                    case 5:
                        startActivity(new Intent(MainActivity.this, CircleProgressActivity.class));
                        break;
                    case 6:
                        startActivity(new Intent(MainActivity.this, MyViewandFiveActivity.class));
                        break;

                    case 7:
                        startActivity(new Intent(MainActivity.this, MyViewandSixActivity.class));
                        break;

                    case 8:
                        startActivity(new Intent(MainActivity.this, MyViewandSevenActivity.class));
                        break;

                    case 9:
                        startActivity(new Intent(MainActivity.this, PorterDuffActivity.class));
                        break;
                    case 10:
                        startActivity(new Intent(MainActivity.this, BezierActivity.class));
                        break;

                    case 11:
                        startActivity(new Intent(MainActivity.this, MyTickActivity.class));
                        break;

                }
                break;
        }
    }


    /*
     * 开启recycleView加载动画
     * */
    private void runLayoutAnimation(final RecyclerView recyclerView, final AnimationItem item) {
        final Context context = recyclerView.getContext();

        final LayoutAnimationController controller =
                AnimationUtils.loadLayoutAnimation(context, item.getResourceId());

        recyclerView.setLayoutAnimation(controller);
        recyclerView.getAdapter().notifyDataSetChanged();
        recyclerView.scheduleLayoutAnimation();
    }
}