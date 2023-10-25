package kz.just_code.browser;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import kz.just_code.browser.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private final String unsplashBaseUrl = "https://source.unsplash.com/800x800/?";
    private String currentImageQuery = "";
    private int cnt_like =  0;
    private int cnt_dislike = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String searchQuery = binding.inputView.getText().toString().trim();

                if (!searchQuery.isEmpty()) {
                    currentImageQuery = searchQuery;
                    displayImageBasedOnQuery(currentImageQuery);
                } else {
                    Toast.makeText(MainActivity.this, "Input is wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });

        binding.btnLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayImageBasedOnQuery(currentImageQuery);
                cnt_like++;
                binding.cntLike.setText(String.valueOf(cnt_like));
            }
        });

        binding.btnDislike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayImageBasedOnQuery(currentImageQuery);
                cnt_dislike++;
                binding.cntDislike.setText(String.valueOf(cnt_dislike));
            }
        });
    }

    private void displayImageBasedOnQuery(String query) {
        String imageUrl = unsplashBaseUrl + query;

        Picasso.get()
                .load(imageUrl)
                .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                .into(binding.imageView);
    }
}
