package poly.manhnd.assignment.utils;

public class PaginationUtil {

	public static String createPagination(int total, int offset, String uri) {
		StringBuilder builder = new StringBuilder();
		final int max = 10;
		final int first = 1;
		final int last = total;
		int next = offset + 1;
		int prev = offset - 1;

		if (total > 1) {
			final String[] link = new String[total + 1];
			for (int i = 1; i <= total; i++) {
				if (i == 1) {
					link[i] = uri;
				} else {
					if(uri.contains("?")) {
						link[i] = String.format("%s&page=%d", uri, i);
					}else {
						link[i] = String.format("%s?page=%d", uri, i);
					}
				}
			}
			builder.append("<nav aria-label='pagination'>");
			builder.append("<ul class='pagination'>");
			if (total > max) {
				if (offset > 5) {
					int start = offset - 4;
					int end = offset + 5;
					if (end > total) {
						start = offset - (max - (total - offset)) + 1;
						builder.append("<li class='page-item'>");
						builder.append("<a class='page-link' href='" + link[1] + "' aria-label='First'>");
						builder.append("<span aria-hidden='true'>&laquo;</span>");
						builder.append("<span class='sr-only'>First</span>");
						builder.append("</a>");
						builder.append("</li>");

						builder.append("<li class='page-item'>");
						builder.append("<a class='page-link' href='" + link[prev] + "' aria-label='Previous'>");
						builder.append("<span aria-hidden='true'>&lt;</span>");
						builder.append("<span class='sr-only'>Previous</span>");
						builder.append("</a>");
						builder.append("</li>");
						for (int i = start; i <= total; i++) {
							if (i == offset) {
								builder.append("<li class=\"page-item active\">");
								builder.append("<a class='page-link' href='" + link[i] + "'>" + i
										+ "<span class='sr-only'>(current)</span></a>");
								builder.append("</li>");
							} else {
								builder.append("<li class='page-item'>");
								builder.append("<a class='page-link' href='" + link[i] + "'>" + i + "</a>");
								builder.append("</li>");
							}
						}
					} else {
						builder.append("<li class='page-item'>");
						builder.append("<a class='page-link' href='" + link[1] + "' aria-label='First'>");
						builder.append("<span aria-hidden='true'>&laquo;</span>");
						builder.append("<span class='sr-only'>First</span>");
						builder.append("</a>");
						builder.append("</li>");

						builder.append("<li class='page-item'>");
						builder.append("<a class='page-link' href='" + link[prev] + "' aria-label='Previous'>");
						builder.append("<span aria-hidden='true'>&lt;</span>");
						builder.append("<span class='sr-only'>Previous</span>");
						builder.append("</a>");
						builder.append("</li>");

						for (int i = start; i <= end; i++) {
							if (i == offset) {
								builder.append("<li class=\"page-item active\">");
								builder.append("<a class='page-link' href='" + link[i] + "'>" + i
										+ "<span class='sr-only'>(current)</span></a>");
								builder.append("</li>");
							} else {
								builder.append("<li class='page-item'>");
								builder.append("<a class='page-link' href='" + link[i] + "'>" + i + "</a>");
								builder.append("</li>");
							}
						}
						builder.append("<li class='page-item'>");
						builder.append("<a class='page-link' href='" + link[next] + "' aria-label='Next'>");
						builder.append("<span aria-hidden='true'>&gt;</span>");
						builder.append("<span class='sr-only'>Next</span>");
						builder.append("</a>");
						builder.append("</li>");

						builder.append("<li class='page-item'>");
						builder.append("<a class='page-link' href='" + link[last] + "' aria-label='Last'>");
						builder.append("<span aria-hidden='true'>&raquo;</span>");
						builder.append("<span class='sr-only'>Last</span>");
						builder.append("</a>");
						builder.append("</li>");
					}
				} else {
					for (int i = first; i <= max; i++) {
						if (i == offset) {
							builder.append("<li class=\"page-item active\">");
							builder.append("<a class='page-link' href='" + link[i] + "'>" + i
									+ "<span class='sr-only'>(current)</span></a>");
							builder.append("</li>");
						} else {
							builder.append("<li class='page-item'>");
							builder.append("<a class='page-link' href='" + link[i] + "'>" + i + "</a>");
							builder.append("</li>");
						}
					}
					builder.append("<li class='page-item'>");
					builder.append("<a class='page-link' href='" + link[next] + "' aria-label='Next'>");
					builder.append("<span aria-hidden='true'>&gt;</span>");
					builder.append("<span class='sr-only'>Next</span>");
					builder.append("</a>");
					builder.append("</li>");

					builder.append("<li class='page-item'>");
					builder.append("<a class='page-link' href='" + link[last] + "' aria-label='Last'>");
					builder.append("<span aria-hidden='true'>&raquo;</span>");
					builder.append("<span class='sr-only'>Last</span>");
					builder.append("</a>");
					builder.append("</li>");
				}
			} else {
				for (int i = first; i <= total; i++) {
					if (i == offset) {
						builder.append("<li class=\"page-item active\">");
						builder.append("<a class='page-link' href='" + link[i] + "'>" + i
								+ "<span class='sr-only'>(current)</span></a>");
						builder.append("</li>");
					} else {
						builder.append("<li class='page-item'>");
						builder.append("<a class='page-link' href='" + link[i] + "'>" + i + "</a>");
						builder.append("</li>");
					}
				}
			}
			builder.append("</ul>");
			builder.append("</nav>");
			return builder.toString();
		}
		return "";
	}
}
