package com.example.cvapp;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Bind views
        ImageView profileImage = findViewById(R.id.profile_image);
        TextView name = findViewById(R.id.name);
        TextView jobTitle = findViewById(R.id.job_title);
        TextView contactInfo = findViewById(R.id.contact_info);
        TextView education = findViewById(R.id.education);
        TextView experience = findViewById(R.id.experience);
        TextView skills = findViewById(R.id.skills);
        TextView projects = findViewById(R.id.projects);
        TextView certifications = findViewById(R.id.certifications);

        // Set user data (Data Science profile)
        name.setText("Ngo Manh Khuong");
        jobTitle.setText("Data Scientist");
        contactInfo.setText("📧 Email: khuongngo1209@gmail.com\n📞 Phone: +123456789\n🔗 LinkedIn: linkedin.com/in/khuongngo");
        education.setText("🎓 Education:\nBachelor in Information Systems - University of Information Technology (2021-2026)");
        experience.setText("💼 Experience:\nData Scientist at ABC Tech (2022-Present)\nMachine Learning Engineer at DEF Analytics (2023-2024)");
        skills.setText("🛠 Skills:\n- Python, R, SQL\n- Machine Learning, Deep Learning\n- Data Visualization (Tableau, Power BI)\n- Big Data (Hadoop, Spark)");
        projects.setText("📊 Projects:\n1. Fraud Detection in Supply Chain using Anomaly Detection\n2. Customer Churn Prediction for E-Commerce\n3. Image-Based Food Recommendation using CNNs");
        certifications.setText("📜 Certifications:\n- Google Data Analytics Professional Certificate\n- IBM Data Science Certificate\n- AWS Certified Machine Learning - Specialty");
    }
}
