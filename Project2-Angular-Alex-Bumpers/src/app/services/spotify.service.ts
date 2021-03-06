import { Injectable } from '@angular/core';
import { Http, Headers, RequestOptions } from '@angular/http';
// import 'rxjs/add/operator/map';
import { map } from "rxjs/operators";

@Injectable()
export class SpotifyService {

  private searchUrl: string;

  constructor(private _http: Http) {
    
  }

  searchMusic(str:string, type='artist') {
    // let headers = new Headers();

    const headerDict = {
      'Authorization': 'Bearer ' + '<insert api key here>'
    }

    const requestOptions = {                                                                                                                                                                                 
      headers: new Headers(headerDict), 
    };
    
    // let options = new RequestOptions({ headers: headers });
    this.searchUrl = 'https://api.spotify.com/v1/search?query='+ str + '&offset=0&limit=6&type='+type+'&market=US';
    return this._http.get(this.searchUrl, requestOptions).pipe(map((res) => res.json()));
  }
}
