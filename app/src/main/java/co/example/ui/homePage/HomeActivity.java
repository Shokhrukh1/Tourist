package co.example.ui.homePage;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.jakewharton.rxbinding2.view.RxView;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import co.example.R;
import co.example.core.BaseActivity;
import co.example.core.ClickableBaseAdapter;
import co.example.ui.aboutService.AboutServiceActivity;
import co.example.ui.aboutUzbekistan.AboutUzbekistanActivity;
import co.example.ui.activateDiscountCard.ActivateDiscountCardActivity;
import co.example.ui.category.CategoryActivity;
import co.example.ui.currencyExchange.CurrencyExchangeActivity;
import co.example.ui.discount.DiscountActivity;
import co.example.ui.favorite.FavoriteActivity;
import co.example.ui.historicalPlaces.HistoricalPlacesActivity;
import co.example.ui.homePage.adapters.SectionAdapter;
import co.example.ui.howToUse.HowToUseActivity;
import co.example.ui.insurance.InsuranceActivity;
import co.example.ui.map.MapActivity;
import co.example.ui.publicOffer.PublicOfferActivity;
import co.example.ui.search.SearchActivity;
import co.example.ui.settings.SettingsActivity;
import co.example.ui.signIn.SignInActivity;
import co.example.ui.touristInformationCard.TouristInformationCardActivity;
import co.example.ui.weather.WeatherActivity;
import co.example.utils.UIUtils;

import static android.graphics.Color.TRANSPARENT;
import static co.example.ui.category.Constants.PARENT;
import static co.example.ui.category.Constants.TITLE;

public class HomeActivity extends BaseActivity implements HomeView, NavigationView.OnNavigationItemSelectedListener, ClickableBaseAdapter.OnItemClickListener {
    @Inject
    HomePresenter presenter;
    @BindView(R.id.rvSections)
    RecyclerView rvSections;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.nav_view)
    NavigationView navigationView;
    @BindView(R.id.llDiscounts)
    LinearLayout llDiscounts;
    @BindView(R.id.llFavorites)
    LinearLayout llFavorites;
    @BindView(R.id.llMap)
    LinearLayout llMap;
    @BindView(R.id.llSos)
    LinearLayout llSos;
    private int[] icons = {R.drawable.icon_uzb, R.drawable.icon_historical_places, R.drawable.icon_hotel, R.drawable.icon_taxi, R.drawable.icon_what_to_eat, R.drawable.icon_what_to_see, R.drawable.icon_where_to_go, R.drawable.icon_what_to_buy, R.drawable.icon_helpful_information, R.drawable.icon_currency_exchange, R.drawable.icon_weather, R.drawable.icon_support_service};
    private List<String> sections;

    @Override
    protected int getLayout() {
        return R.layout.activity_home;
    }

    @Override
    protected void init(@Nullable Bundle savedInstanceState) {
        toolbar.setTitle("");
        toolbar.setNavigationIcon(R.drawable.ic_menu);
        setSupportActionBar(toolbar);

        sections = Arrays.asList(getResources().getStringArray(R.array.sections));

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        drawerLayout.setScrimColor(TRANSPARENT);
        navigationView.getBackground().setAlpha(245);
        navigationView.setNavigationItemSelectedListener(this);

        SectionAdapter adapter = new SectionAdapter(sections, icons);
        adapter.setOnItemClickListener(this);
        rvSections.setLayoutManager(new GridLayoutManager(this, 2));
        rvSections.setAdapter(adapter);

        llDiscounts.setOnClickListener(view -> {
            startActivity(new Intent(this, DiscountActivity.class));
        });

        RxView.clicks(llFavorites).subscribe(o -> {
            /*UIUtils.showAlertDialog(this, R.string.feature_is_not_available, R.string.ok, (dialogInterface, i) -> {
                dialogInterface.dismiss();
            });*/
            startActivity(new Intent(this, FavoriteActivity.class));
        });

        llMap.setOnClickListener(view -> {
            //startActivity(new Intent(this, MapActivity.class));
            UIUtils.showAlertDialog(this, R.string.feature_is_not_available, R.string.ok, (dialogInterface, i) -> {
                dialogInterface.dismiss();
            });
        });

        llSos.setOnClickListener(view -> {
            UIUtils.showAlertDialog(this, R.string.feature_is_not_available, R.string.ok, (dialogInterface, i) -> {
                dialogInterface.dismiss();
            });
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.search) {
            startActivity(new Intent(this, SearchActivity.class));
        }

        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.activate_discount_card:
                UIUtils.showAlertDialog(this, R.string.feature_is_not_available, R.string.ok, (dialogInterface, i) -> {
                    dialogInterface.dismiss();
                });
                //startActivity(new Intent(this, ActivateDiscountCardActivity.class));
                break;
            case R.id.sign_in:
                /*UIUtils.showAlertDialog(this, R.string.feature_is_not_available, R.string.ok, (dialogInterface, i) -> {
                    dialogInterface.dismiss();
                });*/
                startActivity(new Intent(this, SignInActivity.class));
                break;
            case R.id.how_to_use:
                startActivity(new Intent(this, HowToUseActivity.class));
                break;
            case R.id.tourist_information_card:
                startActivity(new Intent(this, TouristInformationCardActivity.class));
                break;
            case R.id.insurance:
                startActivity(new Intent(this, InsuranceActivity.class));
                break;
            case R.id.mobile_connection:
                UIUtils.showAlertDialog(this, R.string.feature_is_not_available, R.string.ok, (dialogInterface, i) -> {
                    dialogInterface.dismiss();
                });
                break;
            case R.id.public_offer:
                startActivity(new Intent(this, PublicOfferActivity.class));
                break;
            case R.id.about_service:
                startActivity(new Intent(this, AboutServiceActivity.class));
                break;
            case R.id.settings:
                startActivity(new Intent(this, SettingsActivity.class));
                break;
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void getData() {

    }

    @Override
    public void onItemClicked(int position) {
        Intent intent;
        switch (position) {
            case 0:
                startActivity(new Intent(this, AboutUzbekistanActivity.class));
                break;
            case 1:
                startActivity(new Intent(this, HistoricalPlacesActivity.class));
                break;
            case 2:
                intent = new Intent(this, CategoryActivity.class);
                intent.putExtra(TITLE, sections.get(position));
                intent.putExtra(PARENT, 7L);

                startActivity(intent);
                break;
            case 3:
                UIUtils.showAlertDialog(this, R.string.feature_is_not_available, R.string.ok, (dialogInterface, i) -> {
                    dialogInterface.dismiss();
                });
                break;
            case 4:
                intent = new Intent(this, CategoryActivity.class);
                intent.putExtra(TITLE, sections.get(position));
                intent.putExtra(PARENT, 3L);

                startActivity(intent);
                break;
            case 5:
                intent = new Intent(this, CategoryActivity.class);
                intent.putExtra(TITLE, sections.get(position));
                intent.putExtra(PARENT, 4L);

                startActivity(intent);
                break;
            case 6:
                intent = new Intent(this, CategoryActivity.class);
                intent.putExtra(TITLE, sections.get(position));
                intent.putExtra(PARENT, 6L);

                startActivity(intent);
                break;
            case 7:
                intent = new Intent(this, CategoryActivity.class);
                intent.putExtra(TITLE, sections.get(position));
                intent.putExtra(PARENT, 2L);

                startActivity(intent);
                break;
            case 8:
                intent = new Intent(this, CategoryActivity.class);
                intent.putExtra(TITLE, sections.get(position));
                intent.putExtra(PARENT, 5L);

                startActivity(intent);
                break;
            case 9:
                startActivity(new Intent(this, CurrencyExchangeActivity.class));
                break;
            case 10:
                startActivity(new Intent(this, WeatherActivity.class));
                break;
            case 11:
                UIUtils.showAlertDialog(this, R.string.feature_is_not_available, R.string.ok, (dialogInterface, i) -> {
                    dialogInterface.dismiss();
                });
                break;
        }
    }

    @Override
    public void onItemClicked(Object item) {

    }
}
