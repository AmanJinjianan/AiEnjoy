package com.aman.aienjoy.ui.main;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.aman.aienjoy.R;
import com.aman.aienjoy.ui.main.utils.EchartOptionUtil;
import com.aman.aienjoy.widget.EchartView;

public class MyActivity extends AppCompatActivity {
    private EchartView lineChart;
    private TextView tv_more;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        tv_more = (TextView) findViewById(R.id.tv_more);
        lineChart = (EchartView)findViewById(R.id.lineChart);
        lineChart.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                //最好在h5页面加载完毕后再加载数据，防止html的标签还未加载完成，不能正常显示
                refreshLineChart();
            }
        });
        tv_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转更多页面
            }
        });
    }

    private void refreshLineChart(){
        Object[] x = new Object[]{
                "Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"
        };
        Object[] y = new Object[]{
                820, 932, 901, 934, 1290, 1330, 1320
        };
        lineChart.refreshEchartsWithOption(EchartOptionUtil.getLineChartOptions(x, y));
    }

}