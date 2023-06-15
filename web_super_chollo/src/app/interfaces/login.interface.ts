export class LoginRequest {
    username: string;
    password: string;

    constructor(username: string, password: string) {
        this.username = username;
        this.password = password;
    }
}

export interface LoginResponse {
    id: string;
    username: string;
    avatar: string;
    fullName: string;
    fechaCreado: string;
    roles: string;
    token: string;
    refreshToken: string;
  }