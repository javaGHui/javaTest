# ProGuard rules for Calculator App

# Keep all public classes and methods
-keep public class * {
    public protected *;
}

# Keep Android support libraries
-keep class androidx.** { *; }
-keep interface androidx.** { *; }

# Keep application classes
-keep class com.example.calculator.** { *; }
