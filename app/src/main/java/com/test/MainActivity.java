package com.test;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.test.dao.User;
import com.test.db.UserDao;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    ListView mListView;
    UserDao userDao;
    DataAdapter dataAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userDao = CustomApplication.getInstances().getDaoSession().getUserDao();
        dataAdapter = new DataAdapter(this);
        setContentView(R.layout.activity_main);
        mListView = (ListView) findViewById(R.id.list_view);
        mListView.setAdapter(dataAdapter);
        dataAdapter.setUserList(userDao.loadAll());
    }

    public void onButtonClick(View view) {
        userDao.insert(new User(null, "郑小福", "12345"));
        dataAdapter.setUserList(userDao.queryBuilder().where(UserDao.Properties.Id.eq("1")).list());
    }


    private static class DataAdapter extends BaseAdapter {

        private List<User> userList = new ArrayList<>();
        private Context mContext;

        public DataAdapter(Context context) {
            mContext = context;
        }

        public void setUserList(List<User> userList) {
            this.userList.clear();
            this.userList.addAll(userList);
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return userList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = LayoutInflater.from(mContext).inflate(R.layout.data_item, null);
                holder = new ViewHolder();
                holder.initView(convertView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.bindData(userList.get(position));
            return convertView;
        }
    }


    private static class ViewHolder {
        TextView name;
        TextView number;

        public void initView(View view) {
            name = (TextView) view.findViewById(R.id.tv_name);
            number = (TextView) view.findViewById(R.id.tv_number);
        }

        public void bindData(User user) {
            name.setText(user.getName());
            number.setText(user.getNumber());
        }
    }
}
