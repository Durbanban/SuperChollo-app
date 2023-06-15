import 'dart:io';

import 'package:flutter/material.dart';
import 'package:flutter_form_bloc/flutter_form_bloc.dart';
import 'package:image_picker/image_picker.dart';

import '../blocs/register/register_bloc.dart';

class RegisterForm extends StatefulWidget {
  const RegisterForm({Key? key}) : super(key: key);

  @override
  State<RegisterForm> createState() => _RegisterFormState();
}

class _RegisterFormState extends State<RegisterForm> {
  File? file;
  final picker = ImagePicker();

  @override
  Widget build(BuildContext context) {
    return BlocProvider(
      create: (context) => RegisterFormBloc(),
      child: Builder(
        builder: (context) {
          final registerFormBloc = context.read<RegisterFormBloc>();

          return Scaffold(
            resizeToAvoidBottomInset: false,
            appBar: AppBar(title: const Text('Regístrate')),
            body: FormBlocListener<RegisterFormBloc, String, String>(
              onSubmitting: (context, state) {
                LoadingDialog.show(context);
              },
              onSubmissionFailed: (context, state) {
                LoadingDialog.hide(context);
              },
              onSuccess: (context, state) {
                LoadingDialog.hide(context);

                /* Navigator.of(context).pushReplacement(
                    MaterialPageRoute(builder: (_) => const SuccessScreen()));*/
                Navigator.pop(context);
              },
              onFailure: (context, state) {
                LoadingDialog.hide(context);

                ScaffoldMessenger.of(context).showSnackBar(
                    SnackBar(content: Text(state.failureResponse!)));
              },
              child: SingleChildScrollView(
                physics: const ClampingScrollPhysics(),
                child: AutofillGroup(
                  child: Column(
                    children: <Widget>[
                      TextFieldBlocBuilder(
                        textFieldBloc: registerFormBloc.username,
                        keyboardType: TextInputType.name,
                        autofillHints: const [
                          AutofillHints.username,
                        ],
                        decoration: const InputDecoration(
                          labelText: 'Nombre de Usuario',
                          prefixIcon: Icon(Icons.emoji_people_outlined),
                        ),
                      ),
                      TextFieldBlocBuilder(
                        textFieldBloc: registerFormBloc.fullName,
                        keyboardType: TextInputType.name,
                        autofillHints: const [AutofillHints.name],
                        decoration: const InputDecoration(
                            labelText: "Nombre completo",
                            prefixIcon: Icon(Icons.people_alt_outlined)),
                      ),
                      passwordWidget(registerFormBloc),
                      verifyPasswordWidget(registerFormBloc),
                      /* SizedBox(
                        width: 250,
                        child: CheckboxFieldBlocBuilder(
                          booleanFieldBloc: registerFormBloc.showSuccessResponse,
                          body: Container(
                            alignment: Alignment.centerLeft,
                            child: const Text('Show success response'),
                          ),
                        ),
                      ), */
                      if (file != null)
                        SizedBox(
                            width: 250, height: 250, child: Image.file(file!)),
                      ElevatedButton(
                        /* onPressed: () => Platform.isAndroid
                            ? displayDialogAndroid(context)
                            : displayDialogIos(context), */
                        onPressed: () async {
                          final pickedFile = await picker.pickImage(
                              source: ImageSource.gallery);
                          setState(() {
                            if (pickedFile != null) {
                              file = File(pickedFile.path);
                            }
                          });
                        },
                        child: const Text('Selecciona una foto de la galería'),
                      ),
                      const SizedBox(
                        height: 10,
                      ),
                      ElevatedButton(
                        onPressed: () {
                          if (file != null) {
                            registerFormBloc.file = file!;
                            registerFormBloc.submit();
                          } else {
                            ScaffoldMessenger.of(context).showSnackBar(
                              const SnackBar(
                                content: Center(
                                  child: Text("Selecciona una foto"),
                                ),
                              ),
                            );
                          }
                        },
                        child: Text('REGISTRARSE'),
                      ),
                    ],
                  ),
                ),
              ),
            ),
          );
        },
      ),
    );
  }

  TextFieldBlocBuilder verifyPasswordWidget(RegisterFormBloc registerFormBloc) {
    return TextFieldBlocBuilder(
      textColor: const MaterialStatePropertyAll(Colors.black),
      cursorColor: Colors.black,
      animateWhenCanShow: true,
      /* obscureText: !_showConfirmPassword, */
      textFieldBloc: registerFormBloc.verifyPassword,
      decoration: InputDecoration(
        labelText: 'Repetir contraseña',
        prefixIcon: const Icon(Icons.lock),
        floatingLabelStyle: const TextStyle(
            color: Colors.black,
            fontSize: 18,
            fontWeight: FontWeight.bold,
            backgroundColor: Colors.white),
        enabledBorder: const OutlineInputBorder(
            borderRadius: BorderRadius.all(Radius.circular(10))),
        focusedBorder: const OutlineInputBorder(
            borderRadius: BorderRadius.all(Radius.circular(10))),
        filled: true,
        fillColor: Colors.white,
        prefixIconColor: Colors.blue,
        contentPadding: const EdgeInsets.symmetric(
          horizontal: 16,
          vertical: 12,
        ),
        suffixIcon: IconButton(
          onPressed: () {
            /* setState(() {
              _showConfirmPassword = !_showConfirmPassword;
            }); */
          },
          icon: Icon(
            /* _showConfirmPassword ? Icons.visibility : Icons.visibility_off, */
            Icons.visibility_off,
            color: Colors.grey,
          ),
        ),
        errorMaxLines: 3,
      ),
    );
  }

  TextFieldBlocBuilder passwordWidget(RegisterFormBloc registerFormBloc) {
    return TextFieldBlocBuilder(
      textColor: const MaterialStatePropertyAll(Colors.black),
      cursorColor: Colors.black,
      animateWhenCanShow: true,
      textFieldBloc: registerFormBloc.password,
      autofillHints: const [AutofillHints.password],
      keyboardType: TextInputType.text,
      decoration: InputDecoration(
        labelText: 'Contraseña',
        prefixIcon: const Icon(Icons.lock),
        floatingLabelStyle: const TextStyle(
            color: Colors.black,
            fontSize: 18,
            fontWeight: FontWeight.bold,
            backgroundColor: Colors.white),
        enabledBorder: const OutlineInputBorder(
            borderRadius: BorderRadius.all(Radius.circular(10))),
        focusedBorder: const OutlineInputBorder(
            borderRadius: BorderRadius.all(Radius.circular(10))),
        filled: true,
        fillColor: Colors.white,
        prefixIconColor: Colors.blue,
        contentPadding: const EdgeInsets.symmetric(
          horizontal: 16,
          vertical: 12,
        ),
        suffixIcon: IconButton(
          onPressed: () {
          },
          icon: Icon(
            Icons.visibility_off,
            color: Colors.grey,
          ),
        ),
        errorMaxLines: 3,
      ),
    );
  }
}

class LoadingDialog extends StatelessWidget {
  static void show(BuildContext context, {Key? key}) => showDialog<void>(
        context: context,
        useRootNavigator: false,
        barrierDismissible: false,
        builder: (_) => LoadingDialog(key: key),
      ).then((_) => FocusScope.of(context).requestFocus(FocusNode()));

  static void hide(BuildContext context) => Navigator.pop(context);

  const LoadingDialog({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return WillPopScope(
      onWillPop: () async => false,
      child: Center(
        child: Card(
          child: Container(
            width: 80,
            height: 80,
            padding: const EdgeInsets.all(12.0),
            child: const CircularProgressIndicator(),
          ),
        ),
      ),
    );
  }
}

class SuccessScreen extends StatelessWidget {
  const SuccessScreen({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            const Icon(Icons.tag_faces, size: 100),
            const SizedBox(height: 10),
            const Text(
              'Success',
              style: TextStyle(fontSize: 54, color: Colors.black),
              textAlign: TextAlign.center,
            ),
            const SizedBox(height: 10),
            ElevatedButton.icon(
              onPressed: () => Navigator.of(context).pushReplacement(
                  MaterialPageRoute(builder: (_) => const RegisterForm())),
              icon: const Icon(Icons.replay),
              label: const Text('AGAIN'),
            ),
          ],
        ),
      ),
    );
  }
}
