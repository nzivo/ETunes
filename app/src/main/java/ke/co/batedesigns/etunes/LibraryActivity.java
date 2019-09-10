package ke.co.batedesigns.etunes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import ke.co.batedesigns.etunes.ui.library.LibraryFragment;

public class LibraryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.library_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, LibraryFragment.newInstance())
                    .commitNow();
        }
    }
}
