package com.edger.listviewdemo;

import android.content.Context;

import com.edger.commonmodule.utils.CommonAdapter;
import com.edger.commonmodule.utils.CommonViewHolder;

import java.util.List;

public class AnimalAdapter extends CommonAdapter<Animal> {

    public AnimalAdapter(List<Animal> list, Context context) {
        super(context, list, R.layout.list_view_demo_item);
    }

    /**
     * 提供抽象方法，设置控件内容
     *
     * @param viewHolder ViewHolder
     * @param animal     数据集
     */
    @Override
    public void setViewContent(CommonViewHolder viewHolder, Animal animal) {
        viewHolder.setText(R.id.serial_number, Integer.toString(viewHolder.getPosition() + 1))
                .setText(R.id.tv_name, animal.getAnimalName() + " x " + animal.getAnimalSerialNumber())
                .setText(R.id.tv_description, animal.getAnimalDescription())
                .setImage(R.id.img_icon, animal.getAnimalIcon());
    }

}
