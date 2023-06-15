import { Injectable } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';

@Injectable({
  providedIn: 'root'
})
export class CommonService {

  constructor(
    private snackBar: MatSnackBar
  ) { }

  mostrarError(mensaje: string,) {
    this.snackBar.open(mensaje, undefined, {
      horizontalPosition: "center",
      verticalPosition: "top",
      duration: 2500,
      panelClass: "snackbar-mostrar-error",
    });
  }

  mostrarSuccess(mensaje: string) {
    this.snackBar.open(mensaje, undefined, {
      horizontalPosition: "center",
      verticalPosition: "top",
      duration: 2500,
      panelClass: "snackbar-mostrar-success"
    })
  }
}
