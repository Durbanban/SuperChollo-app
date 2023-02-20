
import 'package:client_super_chollo/services/services.dart';
import 'package:get_it/get_it.dart'; 
import 'package:injectable/injectable.dart';
import 'package:client_super_chollo/config/locator.config.dart'; 

final getIt = GetIt.instance;


@InjectableInit()
void configureDependencies() => getIt.init();

void setupAsyncDependencies() {
  getIt.registerSingletonAsync<LocalStorageService>(() => LocalStorageService.getInstance());
}