-To get project to work, start by running backend java springboot using mvn spring-boot:run

-Then launch project using emulator.

-If pressing play launches a black screen and emulator does not work, 
try to cold boot virtual device from AVD Manager. Follow instructions to delete files if requested
path: C:\Users\<user>\.android\avd\Pixel_5_API_30.avd

-Confirm that in RetrofitClient.java, hardcoded IPv4 address is updated
Do so by going command line and type ipconfig, retrieve dynamic value of key IPv4 address
