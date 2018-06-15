package co.example.config.common;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import co.example.config.scope.PerActivity;
import co.example.ui.aboutService.AboutServiceActivity;
import co.example.ui.aboutService.di.AboutServiceActivityModule;
import co.example.ui.aboutUzbekistan.AboutUzbekistanActivity;
import co.example.ui.aboutUzbekistan.di.AboutUzbekistanActivityModule;
import co.example.ui.activateDiscountCard.ActivateDiscountCardActivity;
import co.example.ui.activateDiscountCard.di.ActivateDiscountCardActivityModule;
import co.example.ui.category.CategoryActivity;
import co.example.ui.category.di.CategoryActivityModule;
import co.example.ui.companyDescription.CompanyDescriptionActivity;
import co.example.ui.companyDescription.di.CompanyDescriptionActivityModule;
import co.example.ui.companyList.CompanyListActivity;
import co.example.ui.companyList.di.CompanyListActivityModule;
import co.example.ui.currencyExchange.CurrencyExchangeActivity;
import co.example.ui.currencyExchange.di.CurrencyExchangeActivityModule;
import co.example.ui.discount.DiscountActivity;
import co.example.ui.discount.di.DiscountActivityModule;
import co.example.ui.favorite.FavoriteActivity;
import co.example.ui.favorite.di.FavoriteActivityModule;
import co.example.ui.historicalPlaces.HistoricalPlacesActivity;
import co.example.ui.historicalPlaces.di.HistoricalPlacesActivityModule;
import co.example.ui.homePage.HomeActivity;
import co.example.ui.homePage.di.HomeActivityModule;
import co.example.ui.howToUse.HowToUseActivity;
import co.example.ui.howToUse.di.HowToUseActivityModule;
import co.example.ui.insurance.InsuranceActivity;
import co.example.ui.insurance.di.InsuranceActivityModule;
import co.example.ui.languages.LanguagesActivity;
import co.example.ui.languages.di.LanguagesActivityModule;
import co.example.ui.map.MapActivity;
import co.example.ui.map.di.MapActivityModule;
import co.example.ui.publicOffer.PublicOfferActivity;
import co.example.ui.publicOffer.di.PublicOfferActivityModule;
import co.example.ui.regionDescription.RegionDescriptionActivity;
import co.example.ui.regionDescription.di.RegionDescriptionActivityModule;
import co.example.ui.restorePassword.RestorePasswordActivity;
import co.example.ui.restorePassword.di.RestorePasswordActivityModule;
import co.example.ui.search.SearchActivity;
import co.example.ui.search.di.SearchActivityModule;
import co.example.ui.settings.SettingsActivity;
import co.example.ui.settings.di.SettingsActivityModule;
import co.example.ui.sightDescription.SightDescriptionActivity;
import co.example.ui.sightDescription.di.SightDescriptionActivityModule;
import co.example.ui.signIn.SignInActivity;
import co.example.ui.signIn.di.SignInActivityModule;
import co.example.ui.signUp.SignUpActivity;
import co.example.ui.signUp.di.SignUpActivityModule;
import co.example.ui.supportService.SupportServiceActivity;
import co.example.ui.supportService.di.SupportServiceActivityModule;
import co.example.ui.touristInformationCard.TouristInformationCardActivity;
import co.example.ui.touristInformationCard.di.TouristInformationCardActivityModule;
import co.example.ui.weather.WeatherActivity;
import co.example.ui.weather.di.WeatherActivityModule;

/**
 * Created by Portable-Acer on 05.12.2017.
 */

@Module(includes = AndroidSupportInjectionModule.class)
public abstract class AppModule {
    @PerActivity
    @ContributesAndroidInjector(modules = HomeActivityModule.class)
    abstract HomeActivity provideHomeActivity();

    @PerActivity
    @ContributesAndroidInjector(modules = HistoricalPlacesActivityModule.class)
    abstract HistoricalPlacesActivity provideHistoricalPlacesActivity();

    @PerActivity
    @ContributesAndroidInjector(modules = AboutUzbekistanActivityModule.class)
    abstract AboutUzbekistanActivity provideAboutUzbekistanActivity();

    @PerActivity
    @ContributesAndroidInjector(modules = CurrencyExchangeActivityModule.class)
    abstract CurrencyExchangeActivity provideCurrencyExchangeActivity();

    @PerActivity
    @ContributesAndroidInjector(modules = SupportServiceActivityModule.class)
    abstract SupportServiceActivity provideSupportServiceActivity();

    @PerActivity
    @ContributesAndroidInjector(modules = DiscountActivityModule.class)
    abstract DiscountActivity provideDiscountActivity();

    @PerActivity
    @ContributesAndroidInjector(modules = WeatherActivityModule.class)
    abstract WeatherActivity provideWeatherActivity();

    @PerActivity
    @ContributesAndroidInjector(modules = MapActivityModule.class)
    abstract MapActivity provideMapActivity();

    @PerActivity
    @ContributesAndroidInjector(modules = SignInActivityModule.class)
    abstract SignInActivity provideSignInActivity();

    @PerActivity
    @ContributesAndroidInjector(modules = SignUpActivityModule.class)
    abstract SignUpActivity provideSignUpActivity();

    @PerActivity
    @ContributesAndroidInjector(modules = RegionDescriptionActivityModule.class)
    abstract RegionDescriptionActivity provideRegionDescriptionActivity();

    @PerActivity
    @ContributesAndroidInjector(modules = RestorePasswordActivityModule.class)
    abstract RestorePasswordActivity provideRestorePasswordActivity();

    @PerActivity
    @ContributesAndroidInjector(modules = AboutServiceActivityModule.class)
    abstract AboutServiceActivity provideAboutServiceActivity();

    @PerActivity
    @ContributesAndroidInjector(modules = PublicOfferActivityModule.class)
    abstract PublicOfferActivity providePublicOfferActivity();

    @PerActivity
    @ContributesAndroidInjector(modules = HowToUseActivityModule.class)
    abstract HowToUseActivity provideHowToUseActivity();

    @PerActivity
    @ContributesAndroidInjector(modules = InsuranceActivityModule.class)
    abstract InsuranceActivity provideInsuranceActivity();

    @PerActivity
    @ContributesAndroidInjector(modules = ActivateDiscountCardActivityModule.class)
    abstract ActivateDiscountCardActivity provideActivateDiscountCardActivity();

    @PerActivity
    @ContributesAndroidInjector(modules = TouristInformationCardActivityModule.class)
    abstract TouristInformationCardActivity provideTouristInformationCardActivity();

    @PerActivity
    @ContributesAndroidInjector(modules = CompanyListActivityModule.class)
    abstract CompanyListActivity provideCompanyListActivity();

    @PerActivity
    @ContributesAndroidInjector(modules = CompanyDescriptionActivityModule.class)
    abstract CompanyDescriptionActivity provideCompanyDescriptionActivity();

    @PerActivity
    @ContributesAndroidInjector(modules = SearchActivityModule.class)
    abstract SearchActivity provideSearchActivity();

    @PerActivity
    @ContributesAndroidInjector(modules = SightDescriptionActivityModule.class)
    abstract SightDescriptionActivity provideSightDescriptionActivity();

    @PerActivity
    @ContributesAndroidInjector(modules = SettingsActivityModule.class)
    abstract SettingsActivity provideSettingsActivity();

    @PerActivity
    @ContributesAndroidInjector(modules = LanguagesActivityModule.class)
    abstract LanguagesActivity provideLanguagesActivity();

    @PerActivity
    @ContributesAndroidInjector(modules = CategoryActivityModule.class)
    abstract CategoryActivity provideCategoryActivity();

    @PerActivity
    @ContributesAndroidInjector(modules = FavoriteActivityModule.class)
    abstract FavoriteActivity provideFavoriteActivity();
}
