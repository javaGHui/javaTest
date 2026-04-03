# Calculator App

一个使用原生 Java 开发的 Android 计算器应用。

## 功能特点

- 基本四则运算（加、减、乘、除）
- 支持小数运算
- 百分比计算
- 清除和删除功能
- 防止除以零错误
- 使用 BigDecimal 确保计算精度
- 现代化 Material Design 界面

## 项目结构

```
CalculatorApp/
├── build.gradle              # 项目级 Gradle 配置
├── settings.gradle           # Gradle 设置
├── gradle.properties         # Gradle 属性
├── README.md                 # 项目说明
└── app/
    ├── build.gradle          # 应用级 Gradle 配置
    ├── proguard-rules.pro    # ProGuard 规则
    └── src/
        └── main/
            ├── AndroidManifest.xml          # 应用清单
            ├── java/com/example/calculator/
            │   └── MainActivity.java        # 主活动
            └── res/
                ├── layout/
                │   └── activity_main.xml    # 主界面布局
                ├── values/
                │   ├── colors.xml           # 颜色定义
                │   ├── strings.xml          # 字符串资源
                │   ├── styles.xml           # 样式定义
                │   └── themes.xml           # 主题定义
                └── mipmap-hdpi/             # 图标资源
```

## 技术栈

- **语言**: Java
- **最低 SDK**: API 24 (Android 7.0)
- **目标 SDK**: API 34 (Android 14)
- **UI 框架**: ConstraintLayout + GridLayout
- **设计规范**: Material Design

## 如何构建

### 使用 Android Studio

1. 打开 Android Studio
2. 选择 "Open an existing Android Studio project"
3. 选择 `CalculatorApp` 文件夹
4. 等待 Gradle 同步完成
5. 点击 "Run" 按钮或使用快捷键 Shift+F10

### 使用命令行

```bash
# 进入项目目录
cd CalculatorApp

# 构建 APK
./gradlew assembleDebug

# 安装到设备
./gradlew installDebug
```

## 使用说明

- **数字键** (0-9): 输入数字
- **小数点** (.): 输入小数
- **运算符** (+, -, ×, ÷): 选择运算
- **等于** (=): 计算结果
- **清除** (C): 清除所有
- **删除** (DEL): 删除最后一位
- **百分比** (%): 转换为百分比

## 计算精度

应用使用 `BigDecimal` 类进行计算，确保高精度运算，避免浮点数精度问题。

## 界面设计

- 深色背景，清晰易读
- 不同功能按钮使用不同颜色区分
  - 数字按钮: 深灰色
  - 运算符按钮: 橙色
  - 功能按钮: 浅灰色
- 大屏幕显示区域，方便查看计算结果

## 作者

使用原生 Java 开发的 Android 计算器应用。
