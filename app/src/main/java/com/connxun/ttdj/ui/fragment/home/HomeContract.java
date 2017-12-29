package com.connxun.ttdj.ui.fragment.home;


import com.connxun.ttdj.entity.Carousel;
import com.connxun.ttdj.entity.CategoryMenu;
import com.connxun.ttdj.entity.PCard;
import com.connxun.ttdj.ui.base.BasePView;

import java.util.List;



/**
 * Created by sll on 2016/5/11.
 */
public interface HomeContract {
    interface HomeView extends BasePView {

        void showCategoryMenuList(List<CategoryMenu> carouseMenus);

        void showBannerList(List<Carousel> banners);

        void showHotCardList(PCard cards);

    }

    interface HomePresenter {
        void getCategoryMenuList();

        void getBannerList();

        void getHotCard(int page,int length);
    }
}
