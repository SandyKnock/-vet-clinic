import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';
import { AppModule } from './app.module';
import {enableProdMode} from '@angular/core';
enableProdMode(); // включает режим разработчика (при старте из разных браузерах повидение можно регуляровать с одного из них)
const platform = platformBrowserDynamic();
platform.bootstrapModule(AppModule);
