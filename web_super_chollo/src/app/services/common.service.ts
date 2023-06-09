import { Injectable } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';

@Injectable({
  providedIn: 'root'
})
export class CommonService {

  constructor(
    private snackBar: MatSnackBar
  ) { }

  mostrarAlerta(mensaje: string, cabecera: string) {
    this.snackBar.open(mensaje, cabecera, {
      horizontalPosition: "center",
      verticalPosition: "top",
      duration: 2500,
    });
  }
}
