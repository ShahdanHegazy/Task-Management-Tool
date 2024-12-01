import { ApplicationConfig, importProvidersFrom, provideZoneChangeDetection } from '@angular/core';
import {provideRouter, withViewTransitions} from '@angular/router';
import { BrowserAnimationsModule, NoopAnimationsModule } from '@angular/platform-browser/animations';

import { routes } from './app.routes';
import { provideClientHydration } from '@angular/platform-browser';
import { provideHttpClient, withFetch } from '@angular/common/http';

export const appConfig: ApplicationConfig = {
  providers: [provideZoneChangeDetection({ eventCoalescing: true }), provideRouter(routes), provideClientHydration(), provideHttpClient(withFetch()),importProvidersFrom(BrowserAnimationsModule),]
};
