@echo off
chcp 65001 >nul
echo ========================================
echo = Veggie Backend 启动脚本              =
echo ========================================
echo.

REM 检查是否安装了Maven
where mvn >nul 2>nul
if %errorlevel% == 0 (
    echo [OK] 检测到Maven
    goto :run_with_maven
)

REM 检查Maven Wrapper
if exist "mvnw.cmd" (
    echo [OK] 使用Maven Wrapper
    goto :run_with_wrapper
)

echo [ERROR] 未检测到Maven，请先安装Maven或添加Maven到环境变量
echo.
echo 安装指南:
echo 1. 下载Maven: https://maven.apache.org/download.cgi
echo 2. 解压到本地目录
echo 3. 配置环境变量MAVEN_HOME和PATH
pause
exit /b 1

:run_with_maven
    echo.
    echo 正在编译并启动项目...
    call mvn clean spring-boot:run
    goto :end

:run_with_wrapper
    echo.
    echo 正在编译并启动项目...
    call mvnw.cmd clean spring-boot:run
    goto :end

:end
    echo.clear
    echo 项目已停止
    pause
