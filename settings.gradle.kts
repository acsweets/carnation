pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        // 以下四行添加阿里云的仓库地址，方便国内开发者下载相关插件
        maven("https://maven.aliyun.com/repository/jcenter")
        maven { setUrl("https://maven.aliyun.com/repository/google") }
        maven("https://maven.aliyun.com/repository/gradle-plugin")
        maven("https://maven.aliyun.com/repository/public")
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven("https://maven.aliyun.com/repository/jcenter")
        maven { setUrl("https://maven.aliyun.com/repository/google") }
        maven("https://maven.aliyun.com/repository/gradle-plugin")
        maven("https://maven.aliyun.com/repository/public")

    }
}

rootProject.name = "carnation"
include(":app")
include(":lily01")
include(":lily")
include(":lily02")
include(":lily03")
