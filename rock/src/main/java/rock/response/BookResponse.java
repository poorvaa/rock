package rock.response;

import rock.db.model.Book;

public class BookResponse extends AbstractAPIResponse {

	private Book book;
	
	

	public BookResponse() {
		
	}

	public BookResponse(Book book) {
		
		this.book = book;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}
	
	
}
