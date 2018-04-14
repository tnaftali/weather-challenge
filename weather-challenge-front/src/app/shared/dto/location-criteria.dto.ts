export class LocationCriteriaDto {
    constructor(boardName: string, username: string, location: string) {
        this.boardName = boardName;
        this.location = location;
        this.username = username;
    }

    boardName: string;
    location: string;
    username: string;
}
