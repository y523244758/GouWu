package hjy.gouwu.RecyclerView_ItemDecoration;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import hjy.gouwu.R;
import hjy.gouwu.search_home.search_homeActivity;

/**
 * Created by 胡靖宇 on 2017/10/11.
 */

public class Rc_ItemDecoration extends RecyclerView.ItemDecoration {

    Context con;
    private Paint mPaint;
    private float mDividerHeight;

    public Rc_ItemDecoration(Context con) {
        this.con=con;
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.parseColor("#CDC5BF"));
    }


    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        int childCount = parent.getChildCount();

        for ( int i = 0; i < childCount; i++ ) {
            View view = parent.getChildAt(i);

            int index = parent.getChildAdapterPosition(view);
            //第一个ItemView不需要绘制
            if ( index == 0 ) {
                continue;
            }

            float dividerTop = view.getTop() - mDividerHeight;
            float dividerLeft = parent.getPaddingLeft();
            float dividerBottom = view.getTop();
            float dividerRight = parent.getWidth() - parent.getPaddingRight();

            c.drawRect(dividerLeft,dividerTop,dividerRight,dividerBottom,mPaint);
        }
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        if (parent.getChildAdapterPosition(view) != 0){
            //这里直接硬编码为1px
            outRect.top = 20;
            outRect.left=15;
            outRect.right=15;
            mDividerHeight = 20;
        }

    }
}
