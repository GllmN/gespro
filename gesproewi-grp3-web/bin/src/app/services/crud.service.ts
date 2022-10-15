import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export abstract class CrudService<T> {

  constructor(protected httpClient: HttpClient, protected actionUrl: String) {
  }

  create(item: T): Observable<T> {
    return this.httpClient.post<T>(`${this.actionUrl}`, item)
  };

  update(item: T, id: any): Observable<T> {
    return this.httpClient.put<T>(`${this.actionUrl}/${id}`, item)
  };

  delete(id: any): Observable<T> {
    return this.httpClient.delete<T>(`${this.actionUrl}/${id}`)
  };

  getAll(): Observable<T[]> {
    return this.httpClient.get<T[]>(`${this.actionUrl}`)
  }

  getByid(id: number): Observable<T> {
    return this.httpClient.get<T>(`${this.actionUrl}/${id}`);
  }
}
