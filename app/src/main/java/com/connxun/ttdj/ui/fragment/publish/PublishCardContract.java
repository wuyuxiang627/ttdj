package com.connxun.ttdj.ui.fragment.publish;


import com.connxun.ttdj.entity.CategoryMenu;
import com.connxun.ttdj.entity.CategorySub;
import com.connxun.ttdj.ui.base.BasePView;

import java.util.List;


/**
 * Created by sll on 2016/5/11.
 */
public interface PublishCardContract {
    interface HomeView extends BasePView {

        void showCategoryMenuList(List<CategoryMenu> carouseMenus);
        void showCategoryMenuSubList(List<CategorySub> carouseMenus);

    }

    interface PublishCardPresenter {
        void getCategoryList();
        void getCategorySubList(String id);
    }
}
