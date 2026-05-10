package com.example.myappmenu;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.lang.reflect.Field;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    TextView tvResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // ✅ Set Toolbar as the ActionBar — this makes ⋮ appear reliably
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tvResult = findViewById(R.id.tvResult);


        // ── 1. Alert Dialog ──────────────────────────────────────────────
        Button btnAlert = findViewById(R.id.btnAlert);
        btnAlert.setOnClickListener(v -> showAlertDialog());

        // ── 2. Confirmation Dialog ───────────────────────────────────────
        Button btnConfirm = findViewById(R.id.btnConfirm);
        btnConfirm.setOnClickListener(v -> showConfirmDialog());

        // ── 3. Input Dialog ──────────────────────────────────────────────
        Button btnInput = findViewById(R.id.btnInput);
        btnInput.setOnClickListener(v -> showInputDialog());

        // ── 4. Single-Choice Dialog ──────────────────────────────────────
        Button btnSingle = findViewById(R.id.btnSingle);
        btnSingle.setOnClickListener(v -> showSingleChoiceDialog());

        // ── 5. Multi-Choice Dialog ───────────────────────────────────────
        Button btnMulti = findViewById(R.id.btnMulti);
        btnMulti.setOnClickListener(v -> showMultiChoiceDialog());

        // ── 6. Progress Dialog ───────────────────────────────────────────
        Button btnProgress = findViewById(R.id.btnProgress);
        btnProgress.setOnClickListener(v -> showProgressDialog());

        // ── 7. Date Picker Dialog ────────────────────────────────────────
        Button btnDate = findViewById(R.id.btnDate);
        btnDate.setOnClickListener(v -> showDatePickerDialog());

        // ── 8. Time Picker Dialog ────────────────────────────────────────
        Button btnTime = findViewById(R.id.btnTime);
        btnTime.setOnClickListener(v -> showTimePickerDialog());

        // ── 9. Popup Menu ────────────────────────────────────────────────
        Button btnPopup = findViewById(R.id.btnPopup);
        btnPopup.setOnClickListener(v -> showPopupMenu(v));

        // ── 10. Option Menu is shown via the ActionBar (onCreateOptionsMenu) ──

        // ── 11. Context Menu ─────────────────────────────────────────────
        Button btnContext = findViewById(R.id.btnContext);
        registerForContextMenu(btnContext);   // long-press to trigger
    }

    // ════════════════════════════════════════════════════════════════════
    //  DIALOG IMPLEMENTATIONS
    // ════════════════════════════════════════════════════════════════════

    /** 1. Simple Alert Dialog */
    private void showAlertDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Alert Dialog")
                .setMessage("This is a simple alert message.\nTap OK to dismiss.")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton("OK", (dialog, which) ->
                        setResult("Alert dismissed ✔"))
                .show();
    }

    /** 2. Confirmation Dialog (Yes / No) */
    private void showConfirmDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Confirm Action")
                .setMessage("Are you sure you want to delete this item?")
                .setIcon(android.R.drawable.ic_dialog_info)
                .setPositiveButton("Yes", (dialog, which) ->
                        setResult("Confirmed: Item deleted 🗑"))
                .setNegativeButton("No", (dialog, which) ->
                        setResult("Cancelled: Nothing deleted"))
                .setNeutralButton("Maybe Later", (dialog, which) ->
                        setResult("Deferred: Ask me later ⏰"))
                .show();
    }

    /** 3. Input Dialog */
    private void showInputDialog() {
        EditText input = new EditText(this);
        input.setHint("Enter your name");

        new AlertDialog.Builder(this)
                .setTitle("Input Dialog")
                .setMessage("Please enter your name below:")
                .setView(input)
                .setPositiveButton("Submit", (dialog, which) -> {
                    String text = input.getText().toString().trim();
                    setResult(text.isEmpty() ? "No input provided" : "Hello, " + text + "! 👋");
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

    /** 4. Single-Choice (Radio) Dialog */
    private void showSingleChoiceDialog() {
        String[] colors = {"Red 🔴", "Green 🟢", "Blue 🔵", "Yellow 🟡"};
        final int[] chosen = {0};

        new AlertDialog.Builder(this)
                .setTitle("Pick a Color")
                .setSingleChoiceItems(colors, 0, (dialog, which) -> chosen[0] = which)
                .setPositiveButton("Select", (dialog, which) ->
                        setResult("Selected: " + colors[chosen[0]]))
                .setNegativeButton("Cancel", null)
                .show();
    }

    /** 5. Multi-Choice (Checkbox) Dialog */
    private void showMultiChoiceDialog() {
        String[] fruits = {"Apple 🍎", "Banana 🍌", "Cherry 🍒", "Mango 🥭"};
        boolean[] checked = {false, false, false, false};
        StringBuilder sb = new StringBuilder();

        new AlertDialog.Builder(this)
                .setTitle("Pick Fruits")
                .setMultiChoiceItems(fruits, checked, (dialog, which, isChecked) ->
                        checked[which] = isChecked)
                .setPositiveButton("Done", (dialog, which) -> {
                    sb.setLength(0);
                    for (int i = 0; i < fruits.length; i++) {
                        if (checked[i]) sb.append(fruits[i]).append(" ");
                    }
                    setResult(sb.length() == 0 ? "None selected" : "Picked: " + sb.toString().trim());
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

    /** 6. Progress Dialog (spins for 3 seconds then auto-dismisses) */
    @SuppressWarnings("deprecation")
    private void showProgressDialog() {
        ProgressDialog pd = new ProgressDialog(this);
        pd.setTitle("Loading…");
        pd.setMessage("Please wait, fetching data…");
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pd.setCancelable(false);
        pd.show();

        // Auto-dismiss after 3 seconds to simulate a task
        new android.os.Handler().postDelayed(() -> {
            pd.dismiss();
            setResult("Progress complete ✅");
        }, 3000);
    }

    /** 7. Date Picker Dialog */
    private void showDatePickerDialog() {
        Calendar cal = Calendar.getInstance();
        new DatePickerDialog(this,
                (view, year, month, day) ->
                        setResult(String.format("Date chosen: %02d/%02d/%d 📅", day, month + 1, year)),
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)
        ).show();
    }

    /** 8. Time Picker Dialog */
    private void showTimePickerDialog() {
        Calendar cal = Calendar.getInstance();
        new TimePickerDialog(this,
                (view, hour, minute) ->
                        setResult(String.format("Time chosen: %02d:%02d ⏰", hour, minute)),
                cal.get(Calendar.HOUR_OF_DAY),
                cal.get(Calendar.MINUTE),
                true          // 24-hour format
        ).show();
    }

    // ════════════════════════════════════════════════════════════════════
    //  MENU IMPLEMENTATIONS
    // ════════════════════════════════════════════════════════════════════

    /** 9. Popup Menu (anchored to the Popup Menu button) */
    private void showPopupMenu(View anchor) {
        PopupMenu popup = new PopupMenu(this, anchor);
        popup.getMenu().add(0, 1, 0, "Share 📤");
        popup.getMenu().add(0, 2, 1, "Edit ✏️");
        popup.getMenu().add(0, 3, 2, "Print 🖨");
        popup.getMenu().add(0, 4, 3, "Delete 🗑");

        popup.setOnMenuItemClickListener(item -> {
            setResult("Popup → " + item.getTitle());
            return true;
        });
        popup.show();
    }

    /** 10. Options Menu (ActionBar / 3-dot overflow) */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 100, 0, "Settings ⚙️");
        menu.add(0, 101, 1, "Profile 👤");
        menu.add(0, 102, 2, "About ℹ️");
        menu.add(0, 103, 3, "Logout 🔒");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 100: setResult("Options → Settings ⚙️"); return true;
            case 101: setResult("Options → Profile 👤");  return true;
            case 102: setResult("Options → About ℹ️");    return true;
            case 103: setResult("Options → Logout 🔒");   return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /** 11. Context Menu (long-press the "Context Menu" button) */
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Context Menu");
        menu.add(0, 200, 0, "Copy 📋");
        menu.add(0, 201, 1, "Paste 📄");
        menu.add(0, 202, 2, "Cut ✂️");
        menu.add(0, 203, 3, "Select All 🔲");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 200: setResult("Context → Copy 📋");       return true;
            case 201: setResult("Context → Paste 📄");      return true;
            case 202: setResult("Context → Cut ✂️");        return true;
            case 203: setResult("Context → Select All 🔲"); return true;
        }
        return super.onContextItemSelected(item);
    }

    // ════════════════════════════════════════════════════════════════════
    //  HELPER
    // ════════════════════════════════════════════════════════════════════
    private void setResult(String message) {
        tvResult.setText(message);
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}