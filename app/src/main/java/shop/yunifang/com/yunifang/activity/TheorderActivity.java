package shop.yunifang.com.yunifang.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import shop.yunifang.com.yunifang.R;
import shop.yunifang.com.yunifang.fragment.Fragment_all;
import shop.yunifang.com.yunifang.fragment.Fragment_evaluate;
import shop.yunifang.com.yunifang.fragment.Fragment_goods;
import shop.yunifang.com.yunifang.fragment.Fragment_payment;
import shop.yunifang.com.yunifang.fragment.Fragment_shipping;

public class TheorderActivity extends FragmentActivity implements View.OnClickListener{

    private ViewPager ordervp;
    private ImageView orderback;
    private List<Fragment>orderlist;
    private TextView evaluate;
    private TextView all;
    private TextView goods;
    private TextView payment;
    private TextView shipping;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theorder);

        initView();
        loadData();
        setAdapter();
    }

    private void setAdapter() {
    ordervp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
        @Override
        public Fragment getItem(int position) {
            return orderlist.get(position);
        }

        @Override
        public int getCount() {
            return orderlist.size();
        }
    });

    }

    private void loadData() {
        orderlist=new ArrayList<Fragment>();
        Fragment_evaluate evaluate=new Fragment_evaluate();
        Fragment_goods  goods=new Fragment_goods();
        Fragment_payment payment=new Fragment_payment();
        Fragment_shipping shipping=new Fragment_shipping();
        Fragment_all all=new Fragment_all();


        orderlist.add(evaluate);
        orderlist.add(goods);
        orderlist.add(shipping);
        orderlist.add(all);
        orderlist.add(payment);
    }

    private void initView() {
        ordervp=(ViewPager)findViewById(R.id.order_vp);
        orderback=(ImageView)findViewById(R.id.orderback);
        orderback.setOnClickListener(backorderonlist);

        evaluate=(TextView)findViewById(R.id.evaluate);
        all=(TextView)findViewById(R.id.all);
        goods=(TextView)findViewById(R.id.goods);
        payment=(TextView)findViewById(R.id.payment);
        shipping=(TextView)findViewById(R.id.shipping);

        all.setTextColor(Color.RED);
        payment.setTextColor(Color.BLACK);
        shipping.setTextColor(Color.BLACK);
        goods.setTextColor(Color.BLACK);
        evaluate.setTextColor(Color.BLACK);

        all.setOnClickListener(this);
        payment.setOnClickListener(this);
        shipping.setOnClickListener(this);
        goods.setOnClickListener(this);
        evaluate.setOnClickListener(this);



        ordervp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                int currenItem = ordervp.getCurrentItem();
                switch (currenItem){
                    case 0:
                        all.setTextColor(Color.RED);
                        payment.setTextColor(Color.BLACK);
                        shipping.setTextColor(Color.BLACK);
                        goods.setTextColor(Color.BLACK);
                        evaluate.setTextColor(Color.BLACK);
                        break;
                    case 1:
                        all.setTextColor(Color.BLACK);
                        payment.setTextColor(Color.RED);
                        shipping.setTextColor(Color.BLACK);
                        goods.setTextColor(Color.BLACK);
                        evaluate.setTextColor(Color.BLACK);
                        break;
                    case 2:
                        all.setTextColor(Color.BLACK);
                        payment.setTextColor(Color.BLACK);
                        shipping.setTextColor(Color.RED);
                        goods.setTextColor(Color.BLACK);
                        evaluate.setTextColor(Color.BLACK);
                        break;
                    case 3:
                        all.setTextColor(Color.BLACK);
                        payment.setTextColor(Color.BLACK);
                        shipping.setTextColor(Color.BLACK);
                        goods.setTextColor(Color.RED);
                        evaluate.setTextColor(Color.BLACK);
                        break;
                    case 4:
                        all.setTextColor(Color.BLACK);
                        payment.setTextColor(Color.BLACK);
                        shipping.setTextColor(Color.BLACK);
                        goods.setTextColor(Color.BLACK);
                        evaluate.setTextColor(Color.RED);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }
    View.OnClickListener backorderonlist=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            finish();
        }
    };

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.all:
                all.setTextColor(Color.RED);
                payment.setTextColor(Color.BLACK);
                shipping.setTextColor(Color.BLACK);
                goods.setTextColor(Color.BLACK);
                evaluate.setTextColor(Color.BLACK);
                ordervp.setCurrentItem(0);
                break;
            case R.id.payment:
                all.setTextColor(Color.BLACK);
                payment.setTextColor(Color.RED);
                shipping.setTextColor(Color.BLACK);
                goods.setTextColor(Color.BLACK);
                evaluate.setTextColor(Color.BLACK);
                ordervp.setCurrentItem(1);
                break;
            case R.id.shipping:
                all.setTextColor(Color.BLACK);
                payment.setTextColor(Color.BLACK);
                shipping.setTextColor(Color.RED);
                goods.setTextColor(Color.BLACK);
                evaluate.setTextColor(Color.BLACK);
                ordervp.setCurrentItem(2);
                break;
            case R.id.goods:
                all.setTextColor(Color.BLACK);
                payment.setTextColor(Color.BLACK);
                shipping.setTextColor(Color.BLACK);
                goods.setTextColor(Color.RED);
                evaluate.setTextColor(Color.BLACK);
                ordervp.setCurrentItem(3);
                break;
            case R.id.evaluate:
                all.setTextColor(Color.BLACK);
                payment.setTextColor(Color.BLACK);
                shipping.setTextColor(Color.BLACK);
                goods.setTextColor(Color.BLACK);
                evaluate.setTextColor(Color.RED);
                ordervp.setCurrentItem(4);
            break;
        }
    }
}
