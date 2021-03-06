package weight.manager;

import java.util.ArrayList;
import java.util.List;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;

public class LineChart extends View {
    
    private static final int BAR_RADIUS_X = 10;
    private static final int BAR_RADIUS_Y = 10;

    private float mXMin = 0;
    private float mXMax = 0;
    private float mXDistance = 0;
    
    private float mYMin = 0;
    private float mYMax = 0;
    private float mYDistance = 0;
    
    private Paint mTextPaint;
    
    private int mPaddingLeft;
    private int mPaddingRight;
    private int mPaddingTop;
    private int mPaddingBottom;
    
    private float mPointRadius = 6;

    private Paint mAxisPaint;
    private Paint mAxisLabelPaint;
    private Paint mBGPaint;
    private Paint mLinePaint;
    private Paint mPointPaint;
    private Paint mDefaultUserLinePaint;
    
    private List<Line> mLineList = new ArrayList<Line>();
    private List<Point> mPointList = new ArrayList<Point>();
    
    private AxisLabeler mXAxisLabeler;
    
    public LineChart(Context context, AttributeSet attrs) {
        super(context, attrs);
        
        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager wm = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(metrics);
        
        float density = metrics.density;
        
        Resources r = context.getResources();
        
        mTextPaint = new Paint();
        mTextPaint.setTextSize(11 * density);
        mTextPaint.setAntiAlias(true);
        mTextPaint.setColor(context.getResources().getColor(R.drawable.text_nomarl));
        mTextPaint.setFakeBoldText(true);
        
        mAxisPaint = new Paint();
        mAxisPaint.setStyle(Paint.Style.STROKE);
        mAxisPaint.setColor(r.getColor(R.drawable.chart_axis));
        
        mAxisLabelPaint = new Paint();
        mAxisLabelPaint.setTextSize(11 * density);
        mAxisLabelPaint.setAntiAlias(true);
        mAxisLabelPaint.setFakeBoldText(true);
        mAxisLabelPaint.setColor(context.getResources().getColor(R.drawable.chart_axis_label));
        
        mBGPaint = new Paint();
        mBGPaint.setAntiAlias(true);
        mBGPaint.setStyle(Paint.Style.FILL);
        mBGPaint.setColor(r.getColor(R.drawable.chart_bg));
        
        mLinePaint = new Paint();
        mLinePaint.setStyle(Paint.Style.STROKE);
        mLinePaint.setColor(r.getColor(R.drawable.chart_line));
        mLinePaint.setStrokeWidth(2);
        mLinePaint.setAntiAlias(true);
        
        mPointPaint = new Paint();
        mPointPaint.setStyle(Paint.Style.FILL);
        mPointPaint.setColor(r.getColor(R.drawable.chart_point));
        mPointPaint.setAntiAlias(true);
        
        mDefaultUserLinePaint = new Paint();
        mDefaultUserLinePaint.setStyle(Paint.Style.STROKE);
        mDefaultUserLinePaint.setColor(r.getColor(R.drawable.chart_default_user_line));
        mDefaultUserLinePaint.setStrokeWidth(2);
        mDefaultUserLinePaint.setAntiAlias(true);
    }
    
    public void setXAixsLabeler(AxisLabeler labeler){
        mXAxisLabeler = labeler;
    }
    
    public void clear(){
        mLineList.clear();
        mPointList.clear();
        setXAixsLabeler(null);
        
        postInvalidate();
    }
    
    public void addPoint(String label, float x, float y){
        Point point = new Point(label, x, y);
        mPointList.add(point);
    }
    
    public void setXAxis(float min, float max, float distance){
        mXMin = min;
        mXMax = max;
        mXDistance = distance;
    }
    
    public void setyAxis(float min, float max, float distance){
        mYMin = min;
        mYMax = max;
        mYDistance = distance;
    }
    
    protected void calcPadding(){
        mPaddingLeft = (int)mTextPaint.measureText("  " + mYMax);
        mPaddingRight = mPaddingLeft / 2;
        mPaddingTop = (int)((-mTextPaint.ascent() + mTextPaint.descent()) * 2);
        mPaddingBottom = mPaddingTop;
    }
    
    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        
        calcPadding();
        
        int right = getMeasuredWidth();
        int bottom = getMeasuredHeight();
        
        Rect rect;
        

        rect = new Rect(0, 0, right, bottom);
        RectF rectF = new RectF(rect);
        canvas.drawRoundRect(rectF, BAR_RADIUS_X, BAR_RADIUS_Y, mBGPaint);
        

        for(float x = mXMin; x <= mXMax; x += mXDistance){
            canvas.drawLine(x(x), y(mYMin), x(x), y(mYMax), mAxisPaint);
            
            String label;
            if(mXAxisLabeler != null){
                label = mXAxisLabeler.getLabel(x);
            }
            else{
                label = formatNum(x);
            }

            float tx = x(x) - mTextPaint.measureText(label) / 2;
            float ty = y(mYMin) - mTextPaint.ascent();
            canvas.drawText(label, tx, ty, mAxisLabelPaint);
        }
        

        for(float y = mYMin; y <= mYMax; y += mYDistance){
            canvas.drawLine(x(mXMin), y(y), x(mXMax), y(y), mAxisPaint);
            
            String label = formatNum(y) + " ";
            float tx = x(mXMin) - mTextPaint.measureText(label);
            float ty = y(y) - (mTextPaint.ascent() / 2) - mTextPaint.descent() / 2;
            canvas.drawText(label, tx, ty, mAxisLabelPaint);
        }
        

        Paint paint;
        for(Line line: mLineList){
            paint = line.getPaint();
            if(paint == null){
                paint = mDefaultUserLinePaint;
            }
            
            float startX = x(line.startX);
            float startY = y(line.startY);
            float stopX = x(line.stopX);
            float stopY = y(line.stopY);
            canvas.drawLine(startX, startY, stopX, stopY, paint);
            
            float tx = stopX - mTextPaint.measureText(line.label);
            float ty = stopY + mTextPaint.ascent() / 2;
            canvas.drawText(line.label, tx, ty, mTextPaint);
        }
        

        for(int i = 0; i < mPointList.size(); i++){
            if(i + 1 < mPointList.size()){
                Point p1 = mPointList.get(i);
                Point p2 = mPointList.get(i + 1);
                float startX = x(p1.x);
                float startY = y(p1.y);
                float stopX = x(p2.x);
                float stopY = y(p2.y);
                canvas.drawLine(startX, startY, stopX, stopY, mLinePaint);
            }
        }
        

        for(Point point : mPointList){
            float x = x(point.x);
            float y = y(point.y);
            canvas.drawCircle(x, y, mPointRadius, mPointPaint);
            canvas.drawText(point.label, x - mTextPaint.measureText(point.label) / 2, y - mPointRadius - mTextPaint.descent(), mTextPaint);
        }
    }
    
    public void addLine(String label, float startX, float startY, float stopX, float stopY, Paint paint){
        Line line = new Line(label, startX, startY, stopX, stopY);
        line.setPaint(paint);
        mLineList.add(line);
        
        postInvalidate();
    }
    
    protected String formatNum(float num){
        return String.format("%d", (int)num);
    }
    

    protected float x(float x){
        int width = getMeasuredWidth() - mPaddingLeft - mPaddingRight;
        float scale = width / (mXMax - mXMin);

        return (x - mXMin) * scale + mPaddingLeft;
    }
    
    protected float y(float y){
        int height = getMeasuredHeight() - mPaddingTop - mPaddingBottom;
        float scale = height / (mYMax - mYMin);
        
        return height - ((y - mYMin) * scale) + mPaddingTop;
    }
    
    
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec){
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(width, height);
    }
    
    public static class Point{
        public final String label;
        public final float x;
        public final float y;
        public Point(String label, float x, float y){
            this.label = label;
            this.x = x;
            this.y = y;
        }
    }
    
    public static class Line{
        public final String label;
        public final float startX;
        public final float startY;
        public final float stopX;
        public final float stopY;
        public Paint mPaint = null;
        public Line(String label, float startX, float startY, float stopX, float stopY){
            this.label = label;
            this.startX = startX;
            this.startY = startY;
            this.stopX = stopX;
            this.stopY = stopY;
        }
        
        public void setPaint(Paint paint){
            mPaint = paint;
        }
        
        public Paint getPaint(){
            return mPaint;
        }
    }
    
    public static interface AxisLabeler{
        public String getLabel(float axisNum);
    }
}
