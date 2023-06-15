export class RefreshTokenRequest {
    refreshToken: string;

    constructor(refreshToken: string) {
        this.refreshToken = refreshToken;
    }
}

export interface RefreshTokenResponse {
    token: string;
    refreshToken: string;
}