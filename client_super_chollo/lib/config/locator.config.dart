// GENERATED CODE - DO NOT MODIFY BY HAND

// **************************************************************************
// InjectableConfigGenerator
// **************************************************************************

// ignore_for_file: no_leading_underscores_for_library_prefixes
import 'package:client_super_chollo/repositories/authentication_repository.dart'
    as _i5;
import 'package:client_super_chollo/repositories/producto_repository.dart'
    as _i6;
import 'package:client_super_chollo/repositories/supermercado_repository.dart'
    as _i7;
import 'package:client_super_chollo/repositories/usuario_repository.dart'
    as _i4;
import 'package:client_super_chollo/rest/rest_client.dart' as _i3;
import 'package:client_super_chollo/services/authentication_service.dart'
    as _i8;
import 'package:client_super_chollo/services/producto_service.dart' as _i9;
import 'package:client_super_chollo/services/supermercado_service.dart' as _i10;
import 'package:get_it/get_it.dart' as _i1;
import 'package:injectable/injectable.dart' as _i2;

/// ignore_for_file: unnecessary_lambdas
/// ignore_for_file: lines_longer_than_80_chars
extension GetItInjectableX on _i1.GetIt {
  /// initializes the registration of main-scope dependencies inside of [GetIt]
  _i1.GetIt init({
    String? environment,
    _i2.EnvironmentFilter? environmentFilter,
  }) {
    final gh = _i2.GetItHelper(
      this,
      environment,
      environmentFilter,
    );
    gh.singleton<_i3.RestAuthenticatedClient>(_i3.RestAuthenticatedClient());
    gh.singleton<_i3.RestClient>(_i3.RestClient());
    gh.singleton<_i4.UsuarioRepository>(_i4.UsuarioRepository());
    gh.singleton<_i5.AuthenticationRepository>(_i5.AuthenticationRepository());
    gh.singleton<_i6.ProductoRepository>(_i6.ProductoRepository());
    gh.singleton<_i7.SupermercadoRepository>(_i7.SupermercadoRepository());
    gh.singleton<_i8.JwtAuthenticationService>(_i8.JwtAuthenticationService());
    gh.singleton<_i9.ProductoService>(_i9.ProductoService());
    gh.singleton<_i10.SupermercadoService>(_i10.SupermercadoService());
    return this;
  }
}
