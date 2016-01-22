class CommentsController < ApplicationController
  skip_before_filter  :verify_authenticity_token
  respond_to :html, :json

  def index

    @comments = Comment.all
    respond_with @comments
  end
end
