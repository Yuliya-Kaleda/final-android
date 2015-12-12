package nyc.c4q.android.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.Menu;
import android.widget.FrameLayout;
import nyc.c4q.android.R;
import nyc.c4q.android.model.Email;

public class EmailListActivity extends FragmentActivity implements EmailListFragment.OnEmailSelectedListener {

  private static final String EMAIL = "email";
  private boolean isTwoPane = false;
  private FragmentManager fragmentManager;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_email_list);
    determinePaneLayout();
  }

  private void determinePaneLayout() {
    FrameLayout fragmentItemDetail = (FrameLayout) findViewById(R.id.email_detail_container);
    if (fragmentItemDetail != null) {
      isTwoPane = true;
      fragmentManager = getSupportFragmentManager();
      EmailListFragment fragmentItemsList =
          (EmailListFragment) fragmentManager.findFragmentById(R.id.fragment_email_list);
      fragmentItemsList.setActivateOnItemClick(true);
    }
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.items, menu);
    return true;
  }

  @Override
  public void onEmailSelected(Email email) {
    if (isTwoPane) {
      // tablet - single activity with list and detail

      // TODO - use EmailDetailFragment's factory method to create the fragment
      // then add the fragment to the SupportFragmentManager under R.id.email_detail_container
      EmailDetailFragment fragment = EmailDetailFragment.newInstance(email);
      fragmentManager.beginTransaction().add(R.id.email_detail_container, fragment).commit();

    }
    else {
      // mobile - one activity at a time
      // TODO - launch EmailDetailActivity passing "email" extra
      Intent intent = new Intent(this, EmailDetailActivity.class);
      intent.putExtra(EMAIL, email);
      startActivity(intent);
    }
  }
}
