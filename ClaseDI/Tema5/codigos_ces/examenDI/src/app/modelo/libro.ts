import { Villano } from "./villanos";

export interface Libro {
    id: number;
    Year: number;
    Title: string;
    handle: string;
    Publisher: string;
    ISBN: string;
    Pages: number;
    Notes: string[];
    created_at : string;
    Villanos: Villano[];
}
