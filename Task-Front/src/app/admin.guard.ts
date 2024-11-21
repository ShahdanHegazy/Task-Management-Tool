import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { jwtDecode } from 'jwt-decode';
import { DecodedToken } from './interfaces/DecodedToken';

export const adminGuard: CanActivateFn = (route, state) => {
  const router = inject(Router);
  const token = localStorage.getItem('authToken');

  if (token) {
    const decodedToken: DecodedToken = jwtDecode(token);

    if (decodedToken.roleId === 1) {
      return true;
    }
  }
  router.navigate(['/login']); 
  return false;
  
};
