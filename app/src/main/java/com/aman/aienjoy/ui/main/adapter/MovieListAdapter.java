package com.aman.aienjoy.ui.main.adapter;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aman.aienjoy.R;
import com.aman.aienjoy.ui.main.entity.ThreeMovie;

import java.util.List;
import java.util.Random;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.ViewHolder> {
    private List<ThreeMovie> m;
    private Context mContext;

    private static final int[] Imgs={
            R.drawable.movie_1,R.drawable.movie_2,R.drawable.movie_7,R.drawable.movie_10};

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        private ImageView movie1,movie2,movie3;
        public ViewHolder(View view){
            super(view);
            movie1 = (ImageView) view.findViewById(R.id.movie1);
            movie2 = (ImageView) view.findViewById(R.id.movie2);
            movie3 = (ImageView) view.findViewById(R.id.movie3);

            Random random = new Random(SystemClock.elapsedRealtime());//SystemClock.elapsedRealtime() 从开机到现在的毫秒数（手机睡眠(sleep)的时间也包括在内）
            Bitmap bitmap = BitmapFactory.decodeResource(view.getResources(), Imgs[random.nextInt(Imgs.length)]);
            Bitmap outBitmap =getRoundBitmapByShader(bitmap, 500,737,20, 1);
            movie1.setImageBitmap(outBitmap);

            Random random2 = new Random(SystemClock.elapsedRealtime());//SystemClock.elapsedRealtime() 从开机到现在的毫秒数（手机睡眠(sleep)的时间也包括在内）
            Bitmap bitmap2 = BitmapFactory.decodeResource(view.getResources(), Imgs[random2.nextInt(Imgs.length)]);
            Bitmap outBitmap2 =getRoundBitmapByShader(bitmap2, 500,737,20, 1);
            movie2.setImageBitmap(outBitmap2);

            Random random3 = new Random(SystemClock.elapsedRealtime());//SystemClock.elapsedRealtime() 从开机到现在的毫秒数（手机睡眠(sleep)的时间也包括在内）
            Bitmap bitmap3 = BitmapFactory.decodeResource(view.getResources(), Imgs[random3.nextInt(Imgs.length)]);
            Bitmap outBitmap3 =getRoundBitmapByShader(bitmap3, 500,737,20, 1);
            movie3.setImageBitmap(outBitmap3);
        }
    }

    public MovieListAdapter(List<ThreeMovie> ThreeMovie, Context context) {
        m = ThreeMovie;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int Viewtype) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.three_movie_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(m.size()<1)
            return;
//        ThreeMovie ThreeMovie = m.get(position);
//        holder.tvType. setText(ThreeMovie.getType());
    }

    public static Bitmap getRoundBitmapByShader(Bitmap bitmap, int outWidth, int outHeight, int radius, int boarder) {
        if (bitmap == null) {
            return null;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        float widthScale = outWidth * 1f / width;
        float heightScale = outHeight * 1f / height;

        Matrix matrix = new Matrix();
        matrix.setScale(widthScale, heightScale);
        //创建输出的bitmap
        Bitmap desBitmap = Bitmap.createBitmap(outWidth, outHeight, Bitmap.Config.ARGB_8888);
        //创建canvas并传入desBitmap，这样绘制的内容都会在desBitmap上
        Canvas canvas = new Canvas(desBitmap);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        //创建着色器
        BitmapShader bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        //给着色器配置matrix
        bitmapShader.setLocalMatrix(matrix);
        paint.setShader(bitmapShader);
        //创建矩形区域并且预留出border
        RectF rect = new RectF(boarder, boarder, outWidth - boarder, outHeight - boarder);
        //把传入的bitmap绘制到圆角矩形区域内
        canvas.drawRoundRect(rect, radius, radius, paint);

        if (boarder > 0) {
            //绘制boarder
            Paint boarderPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            boarderPaint.setColor(Color.GREEN);
            boarderPaint.setStyle(Paint.Style.STROKE);
            boarderPaint.setStrokeWidth(boarder);
            canvas.drawRoundRect(rect, radius, radius, boarderPaint);
        }
        return desBitmap;
    }


    public int getItemCount() {
        return 5;
    }
}