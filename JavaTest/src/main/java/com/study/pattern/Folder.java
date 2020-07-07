package com.study.pattern;

import java.util.ArrayList;
import java.util.List;

/**
 * 文件夹继承Component
 * componentList表示该文件夹下的子文件或者子文件夹
 */
public class Folder extends Component {
    public Integer level;
    private String name;
    private List<Component> componentList = new ArrayList<Component>();

    public Folder(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void add(Component component) {
        this.componentList.add(component);
    }

    @Override
    public void remove(Component component) {
        this.componentList.remove(component);
    }

    @Override
    public void print() {
        System.out.println(this.getName());
        if (this.level == null) {
            this.level = 1;
        }
        String prefix = "";
        for (int i = 0; i < this.level; i++) {
            prefix += "\t- ";
        }
        for (Component component : this.componentList) {
            //如果是文件则level+1
            if (component instanceof Folder){
                ((Folder)component).level = this.level + 1;
            }
            System.out.print(prefix);
            component.print();
        }
        this.level = null;
    }
}
